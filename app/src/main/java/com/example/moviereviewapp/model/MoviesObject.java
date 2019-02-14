package com.example.moviereviewapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesObject {

    @SerializedName("results")
    List<Movie> list;

    public MoviesObject(List<Movie> list) {
        this.list = list;
    }

    public List<Movie> getList() {
        return list;
    }

}
