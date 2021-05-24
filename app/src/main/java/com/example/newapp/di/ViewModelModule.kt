package com.example.newapp.di

import androidx.lifecycle.ViewModel
import com.example.newapp.presentation.viewmodel.CatFactsListViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CatFactsListViewModel::class)
    abstract fun bindCatFactsListViewModel(viewModel: CatFactsListViewModel) : ViewModel
}