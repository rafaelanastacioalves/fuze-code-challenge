package com.example.rafaelanastacioalves.moby.application

import android.app.Application
import android.util.Log

import com.example.rafaelanastacioalves.moby.repository.AppRepository


class MainApplication : Application() {

    val getAppRepository : AppRepository
        get() = ServiceLocator.provideAppRespository(this)
    override fun onCreate() {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        super.onCreate()
    }

    /**
     * A tree which logs important information for crash reporting.
     */

}
