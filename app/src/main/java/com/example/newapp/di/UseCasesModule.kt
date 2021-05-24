package com.example.newapp.di

import com.example.newapp.domain.CatFactsUseCase
import com.example.newapp.domain.CatFactsUseCaseImpl
import com.example.newapp.repository.CatFactsRepository
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {

    @Provides
    fun providesEntityUseCase(repository: CatFactsRepository): CatFactsUseCase = CatFactsUseCaseImpl(repository)
}