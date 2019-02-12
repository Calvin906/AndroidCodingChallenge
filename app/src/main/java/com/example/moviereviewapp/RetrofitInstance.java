package com.example.moviereviewapp;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RetrofitInstance {

    private static Retrofit retrofit;
    private static final String URL = "http://api.nytimes.com/";

    static Retrofit getRetrofitInstance() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;
    }

    public interface GetData {

        @GET("svc/movies/v2/reviews/dvd-picks.json")
        public Call<MovieLoader> getData(@Query("order") String order, @Query("api-key") String key, @Query("offset") int offset);
    }
}
