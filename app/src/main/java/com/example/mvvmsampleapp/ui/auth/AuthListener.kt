package com.example.mvvmsampleapp.ui.auth

import com.example.mvvmsampleapp.data.db.entities.User
//Callback for updating UI
interface AuthListener {
    // Login is a network operation and will take some time.
    //When the operation is started ,need to display progress bar to the user
    fun onStarted()

    //Called when the authentication is success
    //fun onSuccess(loginResponse: LiveData<String>)
    fun onSuccess(user: User)

    // Called when the authentication is failure
    fun onFailure(message: String)

}