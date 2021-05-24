package com.example.newapp.repository.api

import com.example.newapp.model.CatFact
import io.reactivex.Single
import retrofit2.http.GET

interface CatFactsApi {
    @GET("/facts")
    fun getCatFactsList() : Single<List<CatFact>>
}