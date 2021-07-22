package aya.movie.movieWatch.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;

import aya.ematop.movieWatch.R;
import aya.ematop.movieWatch.databinding.ActivityDetailsBinding;
import aya.movie.movieWatch.network.Constants;
import aya.movie.movieWatch.pojo.MovieModel;

public class DetailsActivity extends AppCompatActivity {

    private ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_details);
        if(getIntent() != null){
            MovieModel movieModel= (MovieModel)getIntent().getSerializableExtra("Movie");

            getSupportActionBar().setTitle(movieModel.getTitle());
            String imageUrl = Constants.IMAGE_BASE_URL + movieModel.getPosterPath();
            Glide.with(getApplicationContext())
                    .load(imageUrl)
                    .into(binding.ivImagePoster);
            binding.tvTitle.setText(movieModel.getTitle());
            binding.tvOverview.setText(movieModel.getOverview());
            binding.ratingBar.setRating((movieModel.getVoteAverage()));
        }
    }
}