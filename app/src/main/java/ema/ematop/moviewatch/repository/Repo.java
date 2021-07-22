package ema.ematop.moviewatch.repository;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ema.ematop.moviewatch.network.RetrofitModule;
import ema.ematop.moviewatch.pojo.MovieModel;
import ema.ematop.moviewatch.pojo.MovieResponseModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class Repo {

    public MutableLiveData<List<MovieModel>> movieList = new MutableLiveData<>();
    public   MutableLiveData<String> error = new MutableLiveData<>();
    public MutableLiveData<List<MovieModel>> searchMovieList = new MutableLiveData<>();

    public static Repo repo;

    public static synchronized  Repo getInstance() {
        if(repo ==null){
            repo = new Repo();
        }
        return  repo;
    }
    public void getMovies(String gener, String apiKey) {
        RetrofitModule.getService()
                .getMovies(gener, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MovieResponseModel>() {


                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull MovieResponseModel movieResponseModel) {

                        Repo.getInstance().movieList.setValue(movieResponseModel.getResults());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        Repo.getInstance().error.setValue(e.getLocalizedMessage());
                    }
                });
                }

    public void searchMovies(String apiKey, String query) {
        RetrofitModule.getService()
                .searchMovies(apiKey, query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieResponseModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MovieResponseModel movieResponseModel) {

                        Repo.getInstance().searchMovieList.setValue(movieResponseModel.getResults());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        Repo.getInstance().error.setValue(e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
