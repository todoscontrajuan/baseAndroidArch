package com.example.newapp.presentation.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.newapp.R
import com.example.newapp.presentation.model.MovieListState
import com.example.newapp.presentation.viewmodel.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MovieListViewModel>()

    private val stateObserver = Observer<MovieListState> { state ->
        when (state) {
            is MovieListState.Loading -> {
                mainText.text = "Loading..."
            }
            is MovieListState.Success -> {
                mainText.text = state.data.first().name
            }
            is MovieListState.Error -> {
                mainText.text = state.errorMessage
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeViewModel()
        savedInstanceState?.let {
            viewModel.loadMovies()
        } ?: viewModel.loadMovies()
        setContentView(R.layout.activity_main)
    }

    private fun observeViewModel() {
        viewModel.stateLiveData.observe(this, stateObserver)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.stateLiveData.removeObserver(stateObserver)
    }
}
