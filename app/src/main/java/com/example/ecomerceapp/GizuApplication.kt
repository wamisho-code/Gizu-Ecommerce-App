package com.example.ecomerceapp

import android.app.Application
import com.cloudinary.android.MediaManager

class GizuApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        MediaManager.init(
            this,
            mapOf(
                "cloud_name" to "ql8tle20",
                "secure" to true
            )
        )

    }
}