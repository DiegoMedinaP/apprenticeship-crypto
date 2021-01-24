package com.example.apprenticeship.utils

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkRequest
import kotlin.properties.Delegates

fun Application.startNetworkCallback() {
    val cm: ConnectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val builder: NetworkRequest.Builder = NetworkRequest.Builder()

    cm.registerNetworkCallback(
        builder.build(),
        object : ConnectivityManager.NetworkCallback() {

            override fun onAvailable(network: android.net.Network) {
                Network.isNetworkConnected = true
            }

            override fun onLost(network: android.net.Network) {
                Network.isNetworkConnected = false
            }
        })
}

object Network {
    var isNetworkConnected: Boolean by Delegates.observable(false) { _, _, _ -> }
}
