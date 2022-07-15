package com.example.newapp.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.newapp.R
import com.example.newapp.presentation.model.MovieListState
import com.example.newapp.presentation.viewmodel.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MovieListViewModel>()
    private lateinit var recyclerAdapter: CustomAdapter

    private val stateObserver = Observer<MovieListState> { state ->
        when (state) {
            is MovieListState.Loading -> {
                progressIndicator.visibility = View.VISIBLE
            }
            is MovieListState.Success -> {
                progressIndicator.visibility = View.GONE
                recyclerView.layoutManager = GridLayoutManager(applicationContext, 2)
                recyclerAdapter = CustomAdapter(applicationContext, state.data)
                recyclerView.adapter = recyclerAdapter
            }
            is MovieListState.Error -> {
                progressIndicator.visibility = View.GONE
                Log.e("BASE APP", state.errorMessage)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeViewModel()
        viewModel.loadMovies()
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
