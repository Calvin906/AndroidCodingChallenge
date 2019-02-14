package com.example.moviereviewapp.model;

import com.google.gson.annotations.SerializedName;

public class MovieLink {

    @SerializedName("type")
    private String type;

    @SerializedName("url")
    private String url;

    @SerializedName("suggested_link_text")
    private String suggested_link_text;

    public MovieLink(String type, String url, String suggested_link_text) {
        this.type = type;
        this.url = url;
        this.suggested_link_text = suggested_link_text;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public String getSuggested_link_text() {
        return suggested_link_text;
    }
}
