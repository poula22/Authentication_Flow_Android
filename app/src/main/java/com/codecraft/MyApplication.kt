package com.codecraft

import android.app.Application
import android.net.ConnectivityManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication:Application(){
    val networkHandler=NetworkHandler()
    override fun onCreate() {
        super.onCreate()
        val connectivityManager = getSystemService(ConnectivityManager::class.java) as ConnectivityManager
        connectivityManager.requestNetwork(networkHandler.networkRequest, networkHandler.networkCallback)
    }
}