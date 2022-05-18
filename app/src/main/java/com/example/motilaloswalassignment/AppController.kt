package com.example.motilaloswalassignment

import android.app.Activity
import android.app.Application
import com.example.motilaloswalassignment.di.component.AppComponent


import javax.inject.Inject
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector


class AppController : Application(), HasActivityInjector {

    @Inject
    internal lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }
}

