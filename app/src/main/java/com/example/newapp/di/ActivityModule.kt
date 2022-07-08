package com.example.newapp.di

import com.example.newapp.repository.MovieRepository
import com.example.newapp.repository.MovieRepositoryImpl
import com.example.newapp.repository.api.ServiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class ActivityModule {

    @Provides
    fun providesMoviesRepository(api: ServiceApi): MovieRepository = MovieRepositoryImpl(api)
}