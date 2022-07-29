package com.example.newapp.repository

import com.example.newapp.model.Movie
import com.example.newapp.model.MovieList
import retrofit2.Response

interface MovieRepository {
    suspend fun getPopularMovieList() : Response<MovieList>
}