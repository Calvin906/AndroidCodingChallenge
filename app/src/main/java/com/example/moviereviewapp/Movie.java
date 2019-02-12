package com.example.moviereviewapp;

import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("display_title")
    private String title;

    @SerializedName("mpaa_rating")
    private String rating;

    @SerializedName("headline")
    private String headline;

    @SerializedName("byline")
    private String reviewer;

    @SerializedName("publication_date")
    private String date;

    @SerializedName("summary_short")
    private String summary;

    @SerializedName("multimedia")
    private MovieThumbnail thumbnail;

    public Movie(String title, String rating, String headline, String reviewer, String date, String summary, MovieThumbnail thumbnail) {
        this.title = title;
        this.rating = rating;
        this.headline = headline;
        this.reviewer = reviewer;
        this.date = date;
        this.summary = summary;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public String getRating() {
        return rating;
    }

    public String getHeadline() {
        return headline;
    }

    public String getReviewer() {
        return reviewer;
    }

    public String getDate() {
        return date;
    }

    public String getSummary() {
        return summary;
    }

    public MovieThumbnail getThumbnail() {
        return thumbnail;
    }
}
