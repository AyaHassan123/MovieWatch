package ema.ematop.moviewatch.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import ema.ematop.moviewatch.R;
import ema.ematop.moviewatch.databinding.ActivityMainBinding;
import ema.ematop.moviewatch.network.Constants;
import ema.ematop.moviewatch.pojo.MovieModel;
import ema.ematop.moviewatch.viewModel.MovieViewModel;

import static ema.ematop.moviewatch.network.Constants.API_KEY;

public class MainActivity extends AppCompatActivity implements MovieAdapter.OnMovieClickListener {

    private ActivityMainBinding binding;
    private MovieViewModel movieViewModel;
    private MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        adapter = new MovieAdapter(getApplicationContext());
        binding.rvMovie.setLayoutManager(new LinearLayoutManager(this));
        binding.rvMovie.setAdapter(adapter);
        adapter.onSetClickListner(this::onMovieClick);

        viewMovies(Constants.POPULAR);
        binding.svSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchMovie(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

    }

    //getMovies
    private void viewMovies(String genre){
        movieViewModel.getMovies(genre, API_KEY);
        movieViewModel.movieList.observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {

                if(movieModels.size() !=0){
                adapter.setList(movieModels);
                adapter.notifyDataSetChanged();
                }
                else{
                   Log.e("error", movieViewModel.error.toString());
                }
            }
        });

    }

    //search for Movies
    private void searchMovie(String query){
        movieViewModel.searchMovie(API_KEY, query);
        movieViewModel.searchMovieList.observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {

                if(movieModels.size() !=0){
                    adapter.setList(movieModels);
                    adapter.notifyDataSetChanged();
                }
                else{
                    Log.e("error", movieViewModel.error.toString());
                }
            }
        });
    }

    ///create menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

   ///select item in menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.switch_popular){
            binding.titlemov.setText("Popular");
            //show movies popular
            viewMovies(Constants.POPULAR);

        }else if(id == R.id.switch_top_rated){
            binding.titlemov.setText("Top Rated");
            //show movies top_rated
            viewMovies(Constants.TOP_RATED);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!binding.svSearchView.isIconified()) {
            binding.svSearchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

    // click on movie
    @Override
    public void onMovieClick(View view, int pos) {

        Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
        MovieModel movieModel = adapter.getMoviePosition(pos);
        intent.putExtra("Movie",movieModel);
        startActivity(intent);
    }
}