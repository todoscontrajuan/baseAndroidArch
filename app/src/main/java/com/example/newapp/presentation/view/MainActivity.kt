package com.example.newapp.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.newapp.databinding.ActivityMainBinding
import com.example.newapp.presentation.model.MovieListState
import com.example.newapp.presentation.viewmodel.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MovieListViewModel>()
    private lateinit var binding: ActivityMainBinding

    private lateinit var recyclerAdapter: CustomAdapter

    private val stateObserver = Observer<MovieListState> { state ->
        when (state) {
            is MovieListState.Loading -> {
                binding.progressIndicator.visibility = View.VISIBLE
            }
            is MovieListState.Success -> {
                binding.progressIndicator.visibility = View.GONE
                binding.recyclerView.layoutManager = GridLayoutManager(applicationContext, 2)
                recyclerAdapter = CustomAdapter(applicationContext, state.data)
                binding.recyclerView.adapter = recyclerAdapter
            }
            is MovieListState.Error -> {
                binding.progressIndicator.visibility = View.GONE
                Log.e("BASE APP", state.errorMessage)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        observeViewModel()
        viewModel.loadMovies()
    }

    private fun observeViewModel() {
        viewModel.stateLiveData.observe(this, stateObserver)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.stateLiveData.removeObserver(stateObserver)
    }
}
