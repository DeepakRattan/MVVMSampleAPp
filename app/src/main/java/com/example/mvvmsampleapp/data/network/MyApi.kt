package com.example.mvvmsampleapp.data.network

import com.example.mvvmsampleapp.data.network.responses.AuthResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

//Interface for Retrofit API calls
//Functions for all apis are defined here
interface MyApi {

    /* @FormUrlEncoded
     @POST("login")
     fun userLogin(
         @Field("email") email: String,
         @Field("password") password: String
     ): Call<ResponseBody>*/

    //FormUrlEncoded needs to be specified for POST
    //function for user login

    // A suspending function is just a regular Kotlin function with an
    // additional suspend modifier which indicates that the function can suspend
    // the execution of a coroutine.It can be paused or resumed at a later time
    @FormUrlEncoded
    @POST("login")
    suspend fun userLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<AuthResponse>

    //companion object is an object that is common for all instances of class
    //Alternate to static in JAVA
    companion object {
        //invoke operator can be called on any instance of class without a method name
        operator fun invoke(): MyApi {
            return Retrofit.Builder()
                .baseUrl("https://api.simplifiedcoding.in/course-apis/mvvm/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }


}