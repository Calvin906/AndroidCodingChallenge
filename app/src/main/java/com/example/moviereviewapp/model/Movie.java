package com.example.moviereviewapp.model;

import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("display_title")
    private String title;

    @SerializedName("mpaa_rating")
    private String rating;

    @SerializedName("critics_pick")
    private int criticPick;

    @SerializedName("headline")
    private String headline;

    @SerializedName("byline")
    private String reviewer;

    @SerializedName("publication_date")
    private String publicationDate;

    @SerializedName("opening_date")
    private String openingDate;

    @SerializedName("date_updated")
    private String dateUpdated;

    @SerializedName("summary_short")
    private String summary;

    @SerializedName("link")
    private MovieLink link;

    @SerializedName("multimedia")
    private MovieThumbnail thumbnail;

    public Movie(String title, String rating, int criticPick, String headline, String reviewer, String publicationDate, String openingDate, String dateUpdated, String summary, MovieLink link, MovieThumbnail thumbnail) {
        this.title = title;
        this.rating = rating;
        this.criticPick = criticPick;
        this.headline = headline;
        this.reviewer = reviewer;
        this.publicationDate = publicationDate;
        this.openingDate = openingDate;
        this.dateUpdated = dateUpdated;
        this.summary = summary;
        this.link = link;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public String getRating() {
        return rating;
    }

    public int getCriticPick() {
        return criticPick;
    }

    public String getHeadline() {
        return headline;
    }

    public String getReviewer() {
        return reviewer;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getOpeningDate() {
        return openingDate;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public String getSummary() {
        return summary;
    }

    public MovieLink getLink() {
        return link;
    }

    public MovieThumbnail getThumbnail() {
        return thumbnail;
    }
}
