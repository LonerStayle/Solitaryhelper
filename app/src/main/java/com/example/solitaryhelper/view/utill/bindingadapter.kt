package com.example.solitaryhelper.view.utill

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun imageUrl(view: ImageView,url:String) = Glide.with(view.context).load(url).into(view)