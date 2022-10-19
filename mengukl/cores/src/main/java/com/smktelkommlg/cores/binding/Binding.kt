package com.smktelkommlg.cores.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.smktelkommlg.cores.R

@BindingAdapter("avatar")
fun avatar(imageView: ImageView, avatar: String) =
    Glide.with(imageView)
        .load(avatar)
        .apply(RequestOptions.circleCropTransform()).placeholder(R.drawable.ic_user)
        .into(imageView)

@BindingAdapter("avatarGallery")
fun avatarGallery(imageView: ImageView, avatar: String) =
    Glide.with(imageView)
        .load(avatar)
        .placeholder(R.drawable.ic_user)
        .into(imageView)