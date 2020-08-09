package com.example.solitaryhelper.view.utill

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.toastShort( @StringRes message:Int) {
    Toast.makeText(this, message , Toast.LENGTH_SHORT).show()
}

fun Context.toastDebugTest(message: String) {
    Toast.makeText(this, message , Toast.LENGTH_SHORT).show()
}