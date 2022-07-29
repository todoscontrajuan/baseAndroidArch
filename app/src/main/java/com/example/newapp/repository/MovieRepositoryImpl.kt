package com.example.newapp.repository

import com.example.newapp.model.MovieList
import com.example.newapp.repository.api.ServiceApi
import retrofit2.Response

class MovieRepositoryImpl(private val api: ServiceApi) : MovieRepository {
    override suspend fun getPopularMovieList(): Response<MovieList> = api.getPopularMoviesList()
}