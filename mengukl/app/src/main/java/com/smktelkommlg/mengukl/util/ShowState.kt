package com.smktelkommlg.mengukl.util

import com.smktelkommlg.mengukl.databinding.FollowFragmentBinding
import com.smktelkommlg.mengukl.databinding.FragmentGalleryBinding
import com.smktelkommlg.mengukl.databinding.HomeFragmentBinding


interface ShowState {

    fun onSuccessState(homeFragmentBinding : HomeFragmentBinding? = null, followFragmentBinding: FollowFragmentBinding? = null, galleryFragmentBinding: FragmentGalleryBinding? = null)
    fun onLoadingState(homeFragmentBinding : HomeFragmentBinding? = null, followFragmentBinding: FollowFragmentBinding? = null, galleryFragmentBinding: FragmentGalleryBinding? = null)
    fun onErrorState(homeFragmentBinding : HomeFragmentBinding? = null, followFragmentBinding: FollowFragmentBinding? = null, galleryFragmentBinding: FragmentGalleryBinding? = null, message: String?)
}