package com.example.mvvmsampleapp.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//Define the function related to coroutines
// Object is a static instance of a class that there is only one of,
// otherwise known as a singleton.
// A Kotlin object is like a class that can't be instantiated so it must be called by name

/* Perhaps the best way to show the difference is to look at the decompiled Kotlin code in Java form.

Here is a Kotlin object and class:

object ExampleObject {
  fun example() {
  }
}

class ExampleClass {
  fun example() {
  }
}

 In order to use the ExampleClass, you need to create an instance of it:
 ExampleClass().example(), but with an object, Kotlin creates a single instance of it for you,
 and you don't ever call it's constructor, instead you just access it's static instance by
 using the name: ExampleObject.example().
*/


// We can directly call main function using class Name (Coroutines) here as per
// the definition of object class
object Coroutines {

    // main is high order function that is taking another function as a parameter
    // main is taking a suspend function named work as a parameter which in turn  will not
    // take any parameter and whose return type is Unit
    // Unit in Kotlin is equivalent to void in JAVA
    // main function will return a job
    fun main(work: suspend (() -> Unit)) =
    //Coroutine scope is main
        //launch returns a job which is returned by main function
        CoroutineScope(Dispatchers.Main).launch {
            //executing work function here
            work()
        }
}