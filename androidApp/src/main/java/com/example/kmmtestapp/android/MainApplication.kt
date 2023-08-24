package com.example.kmmtestapp.android

import android.app.Application
import com.google.firebase.FirebaseApp

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // initialize the firebase
        FirebaseApp.initializeApp(this)
    }
}