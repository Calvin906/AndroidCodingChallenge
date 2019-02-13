package com.example.moviereviewapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.moviereviewapp.api.NYTApi;
import com.example.moviereviewapp.api.RetrofitInstance;
import com.example.moviereviewapp.model.MoviesObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {


    private final String ORDER = "by-date";
    private final String API_KEY = "KoRB4K5LRHygfjCL2AH6iQ7NeUqDAGAB";
    private final int OFFSET = 0;

    private Disposable disposable;
    private MovieAdapter adapter;
    private RecyclerView movieRecyclerView;


    private static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieRecyclerView  = findViewById(R.id.movie_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        movieRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MovieAdapter();

        movieRecyclerView.setAdapter(adapter);

        Retrofit retrofit = RetrofitInstance.getRetrofitInstance();
        NYTApi nytApi = retrofit.create(NYTApi.class);

        disposable = nytApi.getMovies(ORDER, API_KEY, OFFSET)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .cache()
                .subscribe(new Consumer<MoviesObject>() {
                    @Override
                    public void accept(MoviesObject moviesObject) throws Exception {
                        generateData(moviesObject);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null) {
            disposable.dispose();
        }
    }

    private void generateData(MoviesObject movies) {

        adapter.addMovies(movies.getList());
    }
}
