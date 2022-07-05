package com.example.newapp.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.newapp.R
import com.example.newapp.presentation.model.MovieListState
import com.example.newapp.presentation.viewmodel.MovieListViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MovieListViewModel

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
        AndroidInjection.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieListViewModel::class.java)
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
