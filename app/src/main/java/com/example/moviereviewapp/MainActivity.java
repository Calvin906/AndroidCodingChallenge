package com.example.moviereviewapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //?order=by-date&api-key=KoRB4K5LRHygfjCL2AH6iQ7NeUqDAGAB&offset=0"

    private final String ORDER = "by-date";
    private final String API_KEY = "KoRB4K5LRHygfjCL2AH6iQ7NeUqDAGAB";
    private final int OFFSET = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RetrofitInstance.GetData retInstance = RetrofitInstance.getRetrofitInstance().create(RetrofitInstance.GetData.class);
        final Call<MovieLoader> movies = retInstance.getData(ORDER, API_KEY, OFFSET);
        movies.enqueue(new Callback<MovieLoader>() {
            @Override
            public void onResponse(Call<MovieLoader> call, Response<MovieLoader> response) {
                generateData(response.body());
            }

            @Override
            public void onFailure(Call<MovieLoader> call, Throwable t) {
                Log.d("TEST", t.getLocalizedMessage());
            }
        });

    }

    private void generateData(MovieLoader movies) {
        RecyclerView movieRecyclerView  = findViewById(R.id.movie_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        movieRecyclerView.setLayoutManager(linearLayoutManager);
        MovieAdapter adapter = new MovieAdapter();

        adapter.addMovies(movies.getList());
        movieRecyclerView.setAdapter(adapter);
    }
}
