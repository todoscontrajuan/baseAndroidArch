package com.example.newapp.repository

import com.example.newapp.model.CatFact
import io.reactivex.Single

interface CatFactsRepository {
    fun getCatFactsList() : Single<List<CatFact>>
}