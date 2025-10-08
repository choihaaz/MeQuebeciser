package com.choihaaz.mequebeciser

import android.app.Application
import timber.log.Timber

class MeQuebeciserApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}