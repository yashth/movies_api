package com.example.movies_api.common

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData

class ConnectionReceiver(private val connectivityManager: ConnectivityManager) : LiveData<Boolean>() {

    private var LOG_TAG = "ConnectionReceiver"

    constructor(application: Application): this(
        application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    )

    private val networkCallback = @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    object : ConnectivityManager.NetworkCallback(){
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            Log.d(LOG_TAG, "onAvailable network: $network")
            postValue(true)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            Log.d(LOG_TAG, "onLost network: $network")
            postValue(false)
        }

        override fun onUnavailable() {
            super.onUnavailable()
            Log.d(LOG_TAG, "onUnavailable ")
            postValue(false)
        }

        override fun onLosing(network: Network, maxMsToLive: Int) {
            super.onLosing(network, maxMsToLive)
            Log.d(LOG_TAG, "onLosing network: $network ,maxMsToLive: $maxMsToLive")
            postValue(false)
        }



    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onActive() {
        super.onActive()
        Log.d(LOG_TAG, "onActive ")
        val builder = NetworkRequest.Builder()
        connectivityManager.registerNetworkCallback(builder.build(),networkCallback)
        connectivityManager.requestNetwork(builder.build(), networkCallback, 5)

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onInactive() {
        Log.d(LOG_TAG, "onInactive ")
        super.onInactive()

        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

}