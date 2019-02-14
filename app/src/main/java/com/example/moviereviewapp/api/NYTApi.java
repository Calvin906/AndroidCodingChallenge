package com.example.moviereviewapp.api;


import com.example.moviereviewapp.model.MoviesObject;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NYTApi {

    @GET("svc/movies/v2/reviews/dvd-picks.json")
    Observable <MoviesObject> getMovies(@Query("order") String order, @Query("api-key") String key, @Query("offset") int offset);

}

