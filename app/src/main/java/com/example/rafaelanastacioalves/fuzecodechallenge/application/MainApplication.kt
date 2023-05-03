package com.example.rafaelanastacioalves.fuzecodechallenge.application
import AppRepository
import ServiceLocator
import android.app.Application


class MainApplication : Application() {

    val getAppRepository : AppRepository
        get() = ServiceLocator.provideAppRespository(this)
    override fun onCreate() {
        super.onCreate()
    }

    /**
     * A tree which logs important information for crash reporting.
     */

}
