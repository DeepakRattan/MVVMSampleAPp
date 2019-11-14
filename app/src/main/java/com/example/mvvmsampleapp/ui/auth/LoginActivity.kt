package com.example.mvvmsampleapp.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmsampleapp.R
import com.example.mvvmsampleapp.data.db.AppDatabase
import com.example.mvvmsampleapp.data.db.entities.User
import com.example.mvvmsampleapp.data.network.MyApi
import com.example.mvvmsampleapp.data.network.NetworkConnectionInterceptor
import com.example.mvvmsampleapp.data.repositories.UserRepository
import com.example.mvvmsampleapp.databinding.ActivityLoginBinding
import com.example.mvvmsampleapp.ui.home.HomeActivity
import com.example.mvvmsampleapp.util.hide
import com.example.mvvmsampleapp.util.show
import com.example.mvvmsampleapp.util.snackbar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)
        val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        val api = MyApi(networkConnectionInterceptor)
        val db = AppDatabase(this)
        val repository = UserRepository(api, db)
        val factory = AuthViewModelFactory(repository)

        /*This repository instance is needed to instantiate AuthViewModel but the problem is that
         we are not creating the instance of AuthViewModel instead we are getting it through ViewModelProvider
         where we don't have any option to pass the constructor parameter.How to solve this ?

         Answer is that we need to use the ViewModelFactory which will pass the required parameter to our view model
         */


        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        // The double colon operator (::) is used to create a class or a function reference.
        // We are getting the ViewModel from factory
        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        //Set viewModel as binding ViewModel
        binding.viewModel = viewModel
        //Define AuthListener to viewModel
        viewModel.authListener = this

        viewModel.getLoggedInUser().observe(this, Observer { user ->
            if (user != null) {
                Intent(this, HomeActivity::class.java).also {
                    /* We want to start this activity as a fresh activity by closing all the other activities.
                    So we need to set the flags.If we don't do this,on pressing the back button user will see the Login Activity
                    which is a bad practice.
                    * */

                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }

            }


        })
    }

    override fun onStarted() {
        // toast("Login Started")
        //Display progress bar when the login process is started
        //progress_bar.visibility = View.VISIBLE
        progress_bar.show()

    }

    /*override fun onSuccess(loginResponse: LiveData<String>) {
        // toast("Login Success")
        //Hide the progress bar on success


        //Observe the Login response here
        loginResponse.observe(this, Observer {
            progress_bar.hide()
            //it is the implicit name of single parameter inside lambda
            toast(it)
        })
    }*/

    override fun onSuccess(user: User) {
        // toast("Login Success")
        //Hide the progress bar on success
        progress_bar.hide()
        //  root_layout.snackbar("${user.name} is logged in")

        //toast("${user.name} is logged in")
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        root_layout.snackbar(message)
        //toast("$message")
    }
}
