package com.example.mvvmsampleapp.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmsampleapp.R
import com.example.mvvmsampleapp.data.db.entities.User
import com.example.mvvmsampleapp.databinding.ActivityLoginBinding
import com.example.mvvmsampleapp.util.hide
import com.example.mvvmsampleapp.util.show
import com.example.mvvmsampleapp.util.snackbar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)
        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        // The double colon operator (::) is used to create a class or a function reference.
        val viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        //Set viewModel as binding ViewModel
        binding.viewModel = viewModel
        //Define AuthListener to viewModel
        viewModel.authListener = this
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
        root_layout.snackbar("${user.name} is logged in")

        //toast("${user.name} is logged in")
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        root_layout.snackbar("$message")
        //toast("$message")
    }
}
