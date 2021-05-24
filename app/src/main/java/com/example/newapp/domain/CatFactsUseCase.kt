package com.example.newapp.domain

import com.example.newapp.model.CatFactModel
import io.reactivex.Single

interface CatFactsUseCase {
    fun getCatFactsList() : Single<List<CatFactModel>>
}