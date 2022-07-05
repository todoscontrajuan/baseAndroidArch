package com.example.newapp.repository.api

import com.example.newapp.model.Movie
import retrofit2.Response
import retrofit2.http.GET

interface ServiceApi {
    @GET("/movielist.json")
    suspend fun getMovieList() : Response<List<Movie>>
}