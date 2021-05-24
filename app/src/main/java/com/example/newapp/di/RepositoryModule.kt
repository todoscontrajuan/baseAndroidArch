package com.example.newapp.di

import com.example.newapp.repository.CatFactsRepository
import com.example.newapp.repository.CatFactsRepositoryImpl
import com.example.newapp.repository.api.CatFactsApi
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun providesCatFactsRepository(api: CatFactsApi): CatFactsRepository = CatFactsRepositoryImpl(api)

}