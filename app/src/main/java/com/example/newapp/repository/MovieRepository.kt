package com.example.newapp.repository

import com.example.newapp.model.Movie
import retrofit2.Response

interface MovieRepository {
    suspend fun getMovieList() : Response<List<Movie>>
}