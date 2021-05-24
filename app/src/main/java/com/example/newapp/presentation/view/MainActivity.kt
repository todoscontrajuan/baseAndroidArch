package com.example.newapp.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.newapp.R
import com.example.newapp.presentation.model.CatFactsListState
import com.example.newapp.presentation.viewmodel.CatFactsListViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: CatFactsListViewModel

    private val stateObserver = Observer<CatFactsListState> { state ->
        when (state) {
            is CatFactsListState.Loading -> {
                mainText.text = "Loading..."
            }
            is CatFactsListState.Success -> {
                mainText.text = state.data.first().text
            }
            is CatFactsListState.Error -> {
                mainText.text = state.errorMessage
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CatFactsListViewModel::class.java)
        observeViewModel()
        savedInstanceState?.let {
            viewModel.loadCatFacts()
        } ?: viewModel.loadCatFacts()
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
