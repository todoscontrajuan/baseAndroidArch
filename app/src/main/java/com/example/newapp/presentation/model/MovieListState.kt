package com.example.newapp.presentation.model

import com.example.newapp.model.Movie
import com.example.newapp.model.MovieList

sealed class MovieListState {
    data class Success(val data: MovieList) : MovieListState()
    object Loading : MovieListState()
    data class Error(val errorMessage: String) : MovieListState()
}