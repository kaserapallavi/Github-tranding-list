package com.example.motilaloswalassignment.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

import com.example.motilaloswalassignment.di.ViewModelKey
import com.example.motilaloswalassignment.factory.ViewModelFactory
import com.example.motilaloswalassignment.ui.viewmodel.GithubListViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(GithubListViewModel::class)
    protected abstract fun githubListViewModel(githubListViewModel: GithubListViewModel): ViewModel
}