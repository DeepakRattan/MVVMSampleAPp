package com.example.mvvmsampleapp.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmsampleapp.data.network.MyApi
import com.example.mvvmsampleapp.data.network.SafeApiRequest
import com.example.mvvmsampleapp.data.network.responses.AuthResponse

import retrofit2.Response

//Api call will be done here
//Repository performs the API request
// UserRepository is the subclass of SafeApiRequest
class UserRepository : SafeApiRequest() {
    // userLogin() function will be called inside the view model

    // API call without using Coroutine
    /*fun userLogin(email: String, password: String): LiveData<String> {

        val loginResponse = MutableLiveData<String>()
        MyApi().userLogin(email, password)
            .enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    loginResponse.value = t.message
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        loginResponse.value = response.body()?.string()
                    } else {
                        //Failure
                        loginResponse.value = response.errorBody()?.string()
                    }
                }

            })

        return loginResponse

    }*/


    //API call using Coroutine
    //Suspend function can be called from a coroutine or another suspend function
    // So need to make userLogin() function as suspend
    /*suspend fun userLogin(email: String, password: String): Response<AuthResponse> {
        return MyApi().userLogin(email, password)

    }*/


    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest { MyApi().userLogin(email, password) }

    }
}