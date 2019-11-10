package com.example.mvvmsampleapp.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mvvmsampleapp.data.repositories.UserRepository
import com.example.mvvmsampleapp.util.ApiException
import com.example.mvvmsampleapp.util.Coroutines

// This AuthViewModel will be used for both Login and SignUp Activity as the functionalities
// are almost same
class AuthViewModel : ViewModel() {
    //Get Email and password from UI
    var email: String? = null //Using null safety operator ? here
    var password: String? = null

    //Use AuthListener interface to return result to Activity(UI)
    var authListener: AuthListener? = null

    fun onLoginButtonClick(view: View) {
        // ?. is Safe call operator
        //This calls the method if property is not null or returns null if that property is
        // null without throwing Null Pointer Exception
        authListener?.onStarted()

        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            //Display error message
            //How? As we are not inside activity
            // Need to create callback
            authListener?.onFailure("Invalid email or password")
            return
        }
        //Success
        // authListener?.onSuccess()

        // (!!) converts any value to a non-null type and
        // throws a NPE exception if the value is null

        //Bad practice again.No need to create instance of another class inside a class
        // AuthViewModel is dependent on UserRepository .Hence tight coupling is here.Not allowed
        //Skipping the issue for the moment as need to learn dependency injection
        /*val loginResponse = UserRepository().userLogin(email!!, password!!)
        authListener?.onSuccess(loginResponse)*/

        Coroutines.main {
            /* val response = UserRepository().userLogin(email!!, password!!)
            if (response.isSuccessful) {
                authListener?.onSuccess(response.body()?.user!!)
            } else {
                authListener?.onFailure("Error code : ${response.code()}")
            }*/


            // I don't want the view model to worry about the response
            // as it is checking whether it is success or failure.(Above mentioned code )
            //Let us do it at a single place.So i need to create a generic function
            // that will perform the API request and return the response

            try {
                val authResponse = UserRepository().userLogin(email!!, password!!)
                // If user is not null ,let block will be executed

                //The let function is used to execute block of code, access its parameter
                // using it and returns the value which is present in the last line (optional).
                // The let function can also be used as an alternative for if block using
                // safe call operator (?.)

                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)

            } catch (e: ApiException) {
                authListener?.onFailure(e.message!!)
            }



        }

    }
}