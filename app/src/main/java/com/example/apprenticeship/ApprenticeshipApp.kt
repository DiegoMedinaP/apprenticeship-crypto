package com.example.apprenticeship

import android.app.Application
import com.example.apprenticeship.utils.startNetworkCallback
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ApprenticeshipApp : Application(){
    override fun onCreate() {
        super.onCreate()
        startNetworkCallback()
    }
}