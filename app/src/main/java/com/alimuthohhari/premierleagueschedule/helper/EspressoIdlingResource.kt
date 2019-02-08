package com.alimuthohhari.premierleagueschedule.helper

import android.support.test.espresso.IdlingResource

object EspressoIdlingResource {

    private val RESOURCE = "GLOBAL"

    private val mCountingIdlingResource =
        SimpleCountingIdling(RESOURCE)

    val idlingResource: IdlingResource
        get() = mCountingIdlingResource

    fun increment() {
        mCountingIdlingResource.increment()
    }

    fun decrement() {
        mCountingIdlingResource.decrement()
    }
}