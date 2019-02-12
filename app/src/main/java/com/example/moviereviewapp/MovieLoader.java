package com.example.moviereviewapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieLoader {

    @SerializedName("results")
    List<Movie> list;

    public MovieLoader(List<Movie> list) { this.list = list; }

    List<Movie> getList() { return list; }

}
