package com.example.newapp.domain

import com.example.newapp.model.CatFact
import com.example.newapp.model.CatFactModel
import com.example.newapp.repository.CatFactsRepository
import io.reactivex.Single

class CatFactsUseCaseImpl(private val repository: CatFactsRepository) : CatFactsUseCase {
    override fun getCatFactsList(): Single<List<CatFactModel>> {
        return repository.getCatFactsList().map { entity -> entity.map(entityModelMapper) }
    }

    private val entityModelMapper: (CatFact) -> CatFactModel = {
            catFact -> CatFactModel(catFact.user, catFact.text)
    }
}