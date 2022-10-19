package com.smktelkommlg.mengukl.util

import com.asthiseta.bismillahtest.databinding.FollowFragmentBinding
import com.asthiseta.bismillahtest.databinding.FragmentGalleryBinding
import com.asthiseta.bismillahtest.databinding.HomeFragmentBinding

interface ShowState {

    fun onSuccessState(homeFragmentBinding : HomeFragmentBinding? = null, followFragmentBinding: FollowFragmentBinding? = null, galleryFragmentBinding: FragmentGalleryBinding? = null)
    fun onLoadingState(homeFragmentBinding : HomeFragmentBinding? = null, followFragmentBinding: FollowFragmentBinding? = null, galleryFragmentBinding: FragmentGalleryBinding? = null)
    fun onErrorState(homeFragmentBinding : HomeFragmentBinding? = null, followFragmentBinding: FollowFragmentBinding? = null, galleryFragmentBinding: FragmentGalleryBinding? = null, message: String?)
}