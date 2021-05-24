package com.example.newapp.repository

import com.example.newapp.model.CatFact
import com.example.newapp.repository.api.CatFactsApi
import io.reactivex.Single

class CatFactsRepositoryImpl(private val api: CatFactsApi) : CatFactsRepository {
    override fun getCatFactsList(): Single<List<CatFact>> = api.getCatFactsList()
}