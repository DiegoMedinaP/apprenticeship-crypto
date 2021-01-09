package com.example.apprenticeship.utils

import android.app.Application
import android.content.Context
import android.net.*
import android.util.Log
import androidx.fragment.app.Fragment
import kotlin.properties.Delegates

/*fun Fragment.isNetworkAvailable(): Boolean {
    val connectivityManager = requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val nw = connectivityManager.activeNetwork ?: return false
    val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false

    return when {
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        //for other device how are able to connect with Ethernet
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        //for check internet over Bluetooth
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
        else -> false
    }
}*/


fun Application.startNetworkCallback() {
    val cm: ConnectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE)    as ConnectivityManager
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
    var isNetworkConnected: Boolean by Delegates.observable(false) { property, oldValue, newValue ->
        Log.i("Network connectivity", "$newValue")
    }
}

