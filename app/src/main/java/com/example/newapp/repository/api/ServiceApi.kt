package com.example.newapp.repository.api

import com.example.newapp.BuildConfig
import com.example.newapp.model.Movie
import com.example.newapp.model.MovieList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {
    @GET("movie/popular")
    suspend fun getPopularMoviesList(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1) : Response<MovieList>
}