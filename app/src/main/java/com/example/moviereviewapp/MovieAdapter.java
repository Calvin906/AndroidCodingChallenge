package com.example.moviereviewapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviereviewapp.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

interface LinkClickListener {
    void onClick(Movie movie);
}

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie> movies;
    private LinkClickListener listener;

    MovieAdapter(LinkClickListener listener) {
        movies = new ArrayList<>();
        this.listener = listener;
    }

    void addMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_view, parent, false);
        return new MovieViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        MovieViewHolder dataHolder = holder;
        dataHolder.bindViews(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.title_view) TextView title;
        @BindView(R.id.mpaa_rating_view) TextView rating;
        @BindView(R.id.headline_view) TextView headline;
        @BindView(R.id.byline_view) TextView reviewer;
        @BindView(R.id.publication_date_view) TextView publicationDate;
        @BindView(R.id.summary_view) TextView summary;
        @BindView(R.id.thumbnail_image) ImageView thumbnail;

        private final LinkClickListener listener;
        private Movie movie;


        MovieViewHolder(View v, LinkClickListener listener) {
            super(v);
            this.listener = listener;
            ButterKnife.bind(this, itemView);
            v.setOnClickListener(this);
        }

        void bindViews(Movie movie) {
            this.movie = movie;
            title.setText(movie.getTitle());
            rating.setText(movie.getRating());
            headline.setText(movie.getHeadline());
            reviewer.setText(movie.getReviewer());
            publicationDate.setText(movie.getPublicationDate());
            summary.setText(movie.getSummary());
            Picasso.with(itemView.getContext()).load(movie.getThumbnail().getSrc()).into(thumbnail);
        }


        @Override
        public void onClick(View view) {
            listener.onClick(movie);
        }
    }

}
