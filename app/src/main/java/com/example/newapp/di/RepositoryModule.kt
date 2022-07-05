package com.example.newapp.di

import com.example.newapp.repository.MovieRepository
import com.example.newapp.repository.MovieRepositoryImpl
import com.example.newapp.repository.api.ServiceApi
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun providesMoviesRepository(api: ServiceApi): MovieRepository = MovieRepositoryImpl(api)

}