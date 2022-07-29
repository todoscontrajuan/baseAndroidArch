package com.example.newapp.presentation.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newapp.model.MovieList
import com.example.newapp.presentation.model.MovieListState
import com.example.newapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {

    val stateLiveData =  MutableLiveData<MovieListState>()
    var job: Job? = null

    fun loadMovies() {
        stateLiveData.value = MovieListState.Loading
        getPopularMovieList()
    }

    @SuppressLint("CheckResult")
    private fun getPopularMovieList() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getPopularMovieList()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) }
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    private fun onSuccess(results: MovieList) {
        stateLiveData.value = MovieListState.Success(results)
    }

    private fun onError(message: String) {
        stateLiveData.value = message.let { MovieListState.Error(it) }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}