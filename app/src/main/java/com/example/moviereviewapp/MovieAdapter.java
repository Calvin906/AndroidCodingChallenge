package com.example.moviereviewapp;

import android.content.Context;
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

interface linkClickListener {
    void onClick(Movie movie);
}

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie> movies;
    private linkClickListener listener;

    MovieAdapter(linkClickListener listener) {
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
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        MovieViewHolder dataHolder = holder;
        dataHolder.bindViews(holder, position);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        TextView rating;
        TextView headline;
        TextView reviewer;
        TextView date;
        TextView summary;
        ImageView thumbnail;
        Context context;

        public MovieViewHolder(View v) {
            super(v);
            context = v.getContext();
            title = v.findViewById(R.id.title_view);
            rating = v.findViewById(R.id.rating_view);
            headline = v.findViewById(R.id.headline_view);
            reviewer = v.findViewById(R.id.reviewer_view);
            date = v.findViewById(R.id.date_view);
            summary = v.findViewById(R.id.summary_view);
            thumbnail = v.findViewById(R.id.thumbnail_image);
            v.setOnClickListener(this);
        }

        void bindViews(MovieViewHolder viewHolder, int pos) {
            Movie movie = movies.get(pos);
            viewHolder.title.setText(movie.getTitle());
            viewHolder.rating.setText(movie.getRating());
            viewHolder.headline.setText(movie.getHeadline());
            viewHolder.reviewer.setText(movie.getReviewer());
            viewHolder.date.setText(movie.getPublicationDate());
            viewHolder.summary.setText(movie.getSummary());
            Picasso.with(context).load(movie.getThumbnail().getSrc()).into(viewHolder.thumbnail);
        }


        @Override
        public void onClick(View view) {
            listener.onClick(movies.get(getAdapterPosition()));
        }
    }

}
