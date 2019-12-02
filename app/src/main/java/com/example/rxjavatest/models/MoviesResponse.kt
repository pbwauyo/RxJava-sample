package com.example.rxjavatest.models

import com.google.gson.annotations.SerializedName

class MoviesResponse(totalResults: Int, results: List<Movie>) {

    @SerializedName("total_results")
    var totalResults = totalResults

    @SerializedName("results")
    var results = results
}