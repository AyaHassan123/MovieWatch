package ema.ematop.moviewatch.network;


import ema.ematop.moviewatch.pojo.MovieResponseModel;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiMovies {

       @GET("{genre} ")
       Single<MovieResponseModel> getMovies(
            @Path("genre") String genre,
            @Query("api_key") String api_key);


    @GET("/3/search/movie")
    Observable<MovieResponseModel> searchMovies(@Query("api_key") String api_key,
                                          @Query("query") String query);

}
