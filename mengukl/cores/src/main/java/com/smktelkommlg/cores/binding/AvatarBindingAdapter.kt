package com.smktelkommlg.cores.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.request.RequestOptions
import com.smktelkommlg.cores.R
import com.smktelkommlg.cores.utilities.GlideApp

@BindingAdapter("avatar")
fun avatar(imageView : ImageView, avatar:String)=
    GlideApp.with(imageView.context)
        .load(avatar)
        .apply(RequestOptions.centerCropTransform().placeholder(R.drawable.ravioli))
        .into(imageView)