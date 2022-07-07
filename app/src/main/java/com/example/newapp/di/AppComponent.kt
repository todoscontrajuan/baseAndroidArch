package com.example.newapp.di

import com.example.newapp.App
import dagger.Component
import dagger.Provides
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    NetModule::class,
    ViewModelModule::class,
    ViewModelFactoryModule::class,
    ActivityModule::class,
    RepositoryModule::class])
interface AppComponent {
    fun inject(application: App)
}
