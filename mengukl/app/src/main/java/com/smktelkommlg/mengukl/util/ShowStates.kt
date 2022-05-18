package com.smktelkommlg.mengukl.util

import android.view.View
import com.smktelkommlg.mengukl.databinding.FragmentHomeBinding

interface ShowStates {
    fun homeLoading(bindingHome : FragmentHomeBinding? = null)
    fun homeSuccess(bindingHome : FragmentHomeBinding? = null)
    fun homeError(bindingHome : FragmentHomeBinding? = null, message:String?)

    val gone : Int
        get() = View.GONE

    val visible : Int
        get() = View.VISIBLE

}