package com.example.githubuserapp.external.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImage")
fun bindingImage(imageView: ImageView, imageUri: String) {
    Glide.with(imageView.context)
        .load(imageUri)
        .into(imageView)
}