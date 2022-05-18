package com.example.motilaloswalassignment.di.component

import android.app.Application

import com.example.motilaloswalassignment.AppController
import com.example.motilaloswalassignment.di.module.ActivityModule
import com.example.motilaloswalassignment.di.module.ApiModule
import com.example.motilaloswalassignment.di.module.DbModule
import com.example.motilaloswalassignment.di.module.ViewModelModule

import javax.inject.Singleton

import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule


@Component(modules = [ApiModule::class,
    DbModule::class,
    ViewModelModule::class,
    ActivityModule::class,
    AndroidSupportInjectionModule::class])

@Singleton
interface AppComponent {


    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }


    fun inject(appController: AppController)
}
