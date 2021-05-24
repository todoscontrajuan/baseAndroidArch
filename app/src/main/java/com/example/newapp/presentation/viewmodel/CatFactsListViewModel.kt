package com.example.newapp.presentation.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newapp.domain.CatFactsUseCase
import com.example.newapp.model.CatFactModel
import com.example.newapp.presentation.model.CatFactsListState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CatFactsListViewModel @Inject constructor(private val catFactsUseCase: CatFactsUseCase) : ViewModel() {

    val stateLiveData =  MutableLiveData<CatFactsListState>()

    fun loadCatFacts() {
        stateLiveData.value = CatFactsListState.Loading
        getCatFactsList()
    }

    @SuppressLint("CheckResult")
    private fun getCatFactsList() {
        catFactsUseCase.getCatFactsList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::onSuccess, this::onError)
    }

    private fun onSuccess(catFactsList: List<CatFactModel>) {
        stateLiveData.value = CatFactsListState.Success(catFactsList)
    }

    private fun onError(error: Throwable) {
        stateLiveData.value = error.message?.let { CatFactsListState.Error(it) }
    }

}