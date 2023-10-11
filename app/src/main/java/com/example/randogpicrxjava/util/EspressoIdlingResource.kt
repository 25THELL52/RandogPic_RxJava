package com.example.randogpicrxjava.util

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource


object EspressoIdlingResource  {

     const val RESOURCE = "GLOBAL"

    private val mCountingIdlingResource: CountingIdlingResource =
        CountingIdlingResource(RESOURCE)

    fun increment() {
        mCountingIdlingResource.increment()
    }

    fun decrement() {


        mCountingIdlingResource.decrement()
    }

    fun getIdlingResource(): IdlingResource? {
        return mCountingIdlingResource
    }
}

