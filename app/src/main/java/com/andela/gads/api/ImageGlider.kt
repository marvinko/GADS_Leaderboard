package com.andela.gads.api

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("badge")
fun loadImage(view: ImageView, url: String){
    Glide.with(view)
            .load(url)
            .into(view)
}