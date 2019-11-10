package com.example.mvvmsampleapp.data.network

import com.example.mvvmsampleapp.util.ApiException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

abstract class SafeApiRequest {

    // Network operation is an asynchronous operation .So i am using suspend function here
    // Pass API call to function which is another suspend function with no parameter
    // and return Response of type T
    // Generic function for API request

    //Any is the superclass of all the classes in Kotlin like Object in JAVA
    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val error = response.errorBody()?.string()
            val message = StringBuilder()
            //Inside let block convert the error String to a json object
            /* let takes the object it is invoked upon as the parameter and returns the result
             of the lambda expression.Kotlin let is a scoping function wherein the variables
             declared inside the expression cannot be used outside.*/
            error?.let {
                try {
                    message.append(JSONObject(it).getString("message"))
                } catch (e: JSONException) {

                }
                message.append("\n")

            }
            message.append("Error code ${response.code()}")
            throw ApiException(message.toString())
        }
    }
}