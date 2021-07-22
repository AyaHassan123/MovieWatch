package ema.ematop.moviewatch.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ema.ematop.moviewatch.pojo.MovieModel;
import ema.ematop.moviewatch.repository.Repo;

public class MovieViewModel extends ViewModel {

    public MutableLiveData<List<MovieModel>> movieList = new MutableLiveData<>();
    public   MutableLiveData<String> error = new MutableLiveData<>();
    public MutableLiveData<List<MovieModel>> searchMovieList = new MutableLiveData<>();

    private Repo repo;

    public MovieViewModel(){
        repo = Repo.getInstance();
        movieList = repo.movieList;
        error = repo.error;
        searchMovieList = repo.searchMovieList;
    }

    public void getMovies(String gener , String apiKey){
        repo.getMovies(gener, apiKey);
    }
    public void searchMovie(String apiKey, String query){
        repo.searchMovies(apiKey, query);
    }
}
