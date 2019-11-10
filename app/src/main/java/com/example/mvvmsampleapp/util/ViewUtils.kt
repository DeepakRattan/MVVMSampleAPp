package com.example.mvvmsampleapp.util

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

//Extension function provides a facility to add methods to a class without inheriting it
//Adding Extension function toast to Context class
//Adding toast method to Context class
fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

//Extension for progress
//Adding show method to ProgressBar
fun ProgressBar.show() {
    visibility = View.VISIBLE
}

//Extension for progress
//Adding hide method to ProgressBar
fun ProgressBar.hide() {
    visibility = View.GONE
}

//Extension fucntion for Snackbar


fun View.snackbar(message: String) {

    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackbar ->
        snackbar.setAction("OK") {
            snackbar.dismiss()
        }.show()
    }
}

