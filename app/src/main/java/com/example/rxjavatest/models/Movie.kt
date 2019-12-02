package com.example.rxjavatest.models

import com.google.gson.annotations.SerializedName

class Movie(title: String, overview: String, imageUrl: String) {

    @SerializedName("original_title")
    var movieTitle = title

    @SerializedName("overview")
    var movieOverview = overview

    @SerializedName("backdrop_path")
    var movieImageUrl = imageUrl

}