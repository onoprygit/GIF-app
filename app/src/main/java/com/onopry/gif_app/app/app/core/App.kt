package com.onopry.gif_app.app.app.core

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {
    init {
        Log.d("MyAppHasStarted", "started")
    }
}