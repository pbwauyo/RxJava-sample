package com.example.rxjavatest.apiInterface

import com.example.rxjavatest.models.MoviesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    fun getTopRatedMovies(@Query("api_key") apiKey: String): Observable<MoviesResponse>
}