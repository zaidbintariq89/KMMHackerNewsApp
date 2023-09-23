package com.kmmhackernewsapp.android

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.google.firebase.FirebaseApp
import com.kmmhackernewsapp.shared.cache.DatabaseDriverFactory
import com.kmmhackernewsapp.shared.network.NetworkRepo

class MainApplication : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        private lateinit var context: Context
        fun getNetworkRepo() = NetworkRepo(DatabaseDriverFactory(context))
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        // initialize the firebase
        FirebaseApp.initializeApp(this)
    }
}