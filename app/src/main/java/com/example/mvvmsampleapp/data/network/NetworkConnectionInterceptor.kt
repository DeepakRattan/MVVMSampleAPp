package com.example.mvvmsampleapp.data.network

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Response

//Handing internet connection

class NetworkConnectionInterceptor(
    context: Context
) : Interceptor {

    private val applicationContext = context.applicationContext


    //This function will intercept our network call
    // chain instance is representing the request


    override fun intercept(chain: Interceptor.Chain): Response {

    }

    private fun isInternetAvailable(): Boolean {
        // Casting is done using infix operator as in Kotlin

        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            connectivityManager.activeNetwork.also {

            }

    }
}