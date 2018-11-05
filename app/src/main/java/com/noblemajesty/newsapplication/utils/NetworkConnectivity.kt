package com.noblemajesty.newsapplication.utils

import android.content.Context
import android.net.ConnectivityManager

class NetworkConnectivity (private val context: Context) {
    fun isConnected() : Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as? ConnectivityManager
        return connectivityManager?.activeNetworkInfo != null
                && connectivityManager.activeNetworkInfo.isConnected
    }
}