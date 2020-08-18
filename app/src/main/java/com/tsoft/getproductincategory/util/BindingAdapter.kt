package com.tsoft.getproductincategory.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImage")
fun loadImageFromUrl(view: ImageView, url: String) {
    url.let {
        Glide
            .with(view.context)
            .load(url)
            .into(view)
    }
}

@BindingAdapter("cargoFreeVisibility")
fun isCargoFree(view: ImageView, string: String) {
    string.let {
        when (string) {
            "1" -> view.show()
            "0" -> view.hide()
            else -> view.hide()
        }
    }
}