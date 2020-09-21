package com.example.solitaryhelper.view.utill

import android.widget.ImageView
import android.widget.NumberPicker
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.solitaryhelper.view.contents.Contents


@BindingAdapter("imageUrl")
fun imageUrl(view: ImageView, url: String) =
    Glide.with(view.context).load(url).dontAnimate().into(view)

@BindingAdapter("imageUrlFitCenter")
fun imageUrlFitCenter(view: ImageView, url: String) =
    Glide.with(view.context).load(url).fitCenter().into(view)

@BindingAdapter("min")
fun min(view: NumberPicker, min: Int) {
    view.minValue = min
}

@BindingAdapter("max")
fun max(view: NumberPicker, max: Int) {
    view.maxValue = max
}




