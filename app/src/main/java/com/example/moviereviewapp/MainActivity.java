package com.example.moviereviewapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final String ORDER = "by-date";
    private final String API_KEY = "KoRB4K5LRHygfjCL2AH6iQ7NeUqDAGAB";
    private final int OFFSET = 0;

    private static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RetrofitInstance.GetData retInstance = RetrofitInstance.getRetrofitInstance().create(RetrofitInstance.GetData.class);
        final Call<MoviesObject> movies = retInstance.getData(ORDER, API_KEY, OFFSET);
        movies.enqueue(new Callback<MoviesObject>() {
            @Override
            public void onResponse(Call<MoviesObject> call, Response<MoviesObject> response) {
                if (response.isSuccessful() && response.body() != null) {
                    generateData(response.body());
                } else {
                    Log.d(TAG, response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<MoviesObject> call, Throwable t) {
                Log.e(TAG, t.getLocalizedMessage());
            }
        });

    }

    private void generateData(MoviesObject movies) {
        RecyclerView movieRecyclerView  = findViewById(R.id.movie_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        movieRecyclerView.setLayoutManager(linearLayoutManager);
        MovieAdapter adapter = new MovieAdapter();

        adapter.addMovies(movies.getList());
        movieRecyclerView.setAdapter(adapter);
    }
}
