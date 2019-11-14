package com.example.mvvmsampleapp.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.example.mvvmsampleapp.util.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

//Handing internet connection

class NetworkConnectionInterceptor(
    context: Context
) : Interceptor {

    private val applicationContext = context.applicationContext


    //This function will intercept our network call
    // Inside chain instance we have request

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable())
            throw NoInternetException("Please check your internet exception")
        return chain.proceed(chain.request())

    }

    // To check whether network is available or not
    private fun isInternetAvailable(): Boolean {
        var connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.activeNetworkInfo.also {

            return it != null && it.isConnected
        }
    }

}