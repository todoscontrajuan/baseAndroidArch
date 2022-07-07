package com.example.newapp.presentation.model

import com.example.newapp.model.Movie

sealed class MovieListState {
    data class Success(val data: List<Movie>) : MovieListState()
    object Loading : MovieListState()
    data class Error(val errorMessage: String) : MovieListState()
}