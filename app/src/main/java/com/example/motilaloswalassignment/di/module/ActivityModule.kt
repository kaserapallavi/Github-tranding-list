package com.example.motilaloswalassignment.di.module



import com.example.motilaloswalassignment.ui.activity.GithubListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun contributeGithubListActivity(): GithubListActivity
}