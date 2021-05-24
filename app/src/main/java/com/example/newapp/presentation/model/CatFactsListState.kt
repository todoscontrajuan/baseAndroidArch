package com.example.newapp.presentation.model

import com.example.newapp.model.CatFactModel

sealed class CatFactsListState {
    data class Success(val data: List<CatFactModel>) : CatFactsListState()
    object Loading : CatFactsListState()
    data class Error(val errorMessage: String) : CatFactsListState()
}