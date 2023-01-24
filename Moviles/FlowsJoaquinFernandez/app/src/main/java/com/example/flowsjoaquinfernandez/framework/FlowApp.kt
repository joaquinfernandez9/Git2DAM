package com.example.flowsjoaquinfernandez.framework

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FlowApp : Application() {

    override fun onCreate() {
        super.onCreate()

    }
}