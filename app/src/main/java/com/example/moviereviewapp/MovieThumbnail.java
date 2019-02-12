package com.example.moviereviewapp;

import com.google.gson.annotations.SerializedName;

public class MovieThumbnail {

    @SerializedName("type")
    private String type;

    @SerializedName("src")
    private String src;

    @SerializedName("width")
    private int width;

    @SerializedName("height")
    private int height;

    public MovieThumbnail(String type, String src, int width, int height) {
        this.type = type;
        this.src = src;
        this.width = width;
        this.height = height;
    }

    public String getType() {
        return type;
    }

    public String getSrc() {
        return src;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
