package com.example.moviereviewapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviereviewapp.model.Movie;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

interface MovieAdapterListener {
    void onClick(Movie movie);

    void onRequestLoadMore(int index);
}

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie> movies;
    private MovieAdapterListener listener;

    MovieAdapter(MovieAdapterListener listener) {
        movies = new ArrayList<>();
        this.listener = listener;
    }

    void addMovies(List<Movie> movies) {
        this.movies.addAll(movies);
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
        if (position == getItemCount() - 1) {
            listener.onRequestLoadMore(position);
        }
        MovieViewHolder dataHolder = holder;
        dataHolder.bindViews(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.title_view)
        TextView title;
        @BindView(R.id.mpaa_rating_view)
        TextView rating;
        @BindView(R.id.critic_pick_view)
        TextView criticPick;
        @BindView(R.id.byline_view)
        TextView byline;
        @BindView(R.id.headline_view)
        TextView headline;
        @BindView(R.id.summary_view)
        TextView summary;
        @BindView(R.id.publication_date_view)
        TextView publicationDate;
        @BindView(R.id.opening_date_view)
        TextView openingDate;
        @BindView(R.id.date_updated_view)
        TextView dateUpdated;
        @BindView(R.id.thumbnail_image)
        ImageView thumbnail;

        private final MovieAdapterListener listener;
        private Movie movie;
        private static final String TAG = "VIEW_HOLDER";
        private final SimpleDateFormat oldFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        private final SimpleDateFormat newFormatter = new SimpleDateFormat("MM/dd/yyyy - hh:mm a");

        MovieViewHolder(View v, MovieAdapterListener listener) {
            super(v);
            this.listener = listener;
            ButterKnife.bind(this, itemView);
            v.setOnClickListener(this);
        }

        void bindViews(Movie movie) {
            this.movie = movie;
            title.setText(movie.getTitle());
            rating.setText(movie.getRating());
            criticPick.setText(itemView.getContext().getString(R.string.critic_pick,movie.getCriticPick()));
            byline.setText(itemView.getContext().getString(R.string.reviewer, movie.getReviewer()));
            headline.setText(movie.getHeadline());
            summary.setText(itemView.getContext().getString(R.string.summary, movie.getSummary()));
            publicationDate.setText(itemView.getContext().getString(R.string.publication_date, movie.getPublicationDate()));
            openingDate.setText(itemView.getContext().getString(R.string.opening_date, movie.getOpeningDate()));
            dateUpdated.setText(itemView.getContext().getString(R.string.updated_date, convertToLocal(movie.getDateUpdated())));
            Picasso.with(itemView.getContext()).load(movie.getThumbnail().getSrc()).into(thumbnail);
        }

        private String convertToLocal(String date) {

            oldFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date value = null;
            String localDate = "";
            try {
                value = oldFormat.parse(date);

                newFormatter.setTimeZone(TimeZone.getDefault());
                localDate = newFormatter.format(value);
            } catch (ParseException e) {
                Log.e(TAG, e.getMessage());
            }

            return localDate;
        }


        @Override
        public void onClick(View view) {
            listener.onClick(movie);
        }
    }

}
