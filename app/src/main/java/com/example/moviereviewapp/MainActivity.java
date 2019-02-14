package com.example.moviereviewapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.moviereviewapp.api.NYTApi;
import com.example.moviereviewapp.api.RetrofitInstance;
import com.example.moviereviewapp.model.Movie;
import com.example.moviereviewapp.model.MoviesObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements LinkClickListener {


    private final String ORDER = "by-publicationDate";
    private final String API_KEY = "KoRB4K5LRHygfjCL2AH6iQ7NeUqDAGAB";
    private final int OFFSET = 0;

    private Disposable disposable;
    private MovieAdapter adapter;
    @BindView(R.id.movie_recycler_view) RecyclerView movieRecyclerView;


    private static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        movieRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MovieAdapter(this);

        movieRecyclerView.setAdapter(adapter);

        Retrofit retrofit = RetrofitInstance.getRetrofitInstance();
        NYTApi nytApi = retrofit.create(NYTApi.class);

        disposable = nytApi.getMovies(ORDER, API_KEY, OFFSET)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .cache()
                .retry(5)
                .subscribe(new Consumer<MoviesObject>() {
                    @Override
                    public void accept(MoviesObject moviesObject) throws Exception {
                        generateData(moviesObject);
                        Log.v(TAG, "Reviews Downloaded");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(MainActivity.this, "Error retrieving reviews", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, throwable.getMessage());
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null) {
            Log.d(TAG, "Dispose Called");
            disposable.dispose();
        }
    }

    private void generateData(MoviesObject movies) {
        adapter.addMovies(movies.getList());
    }

    @Override
    public void onClick(Movie movie) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(movie.getLink().getUrl()));
        startActivity(intent);
    }
}
