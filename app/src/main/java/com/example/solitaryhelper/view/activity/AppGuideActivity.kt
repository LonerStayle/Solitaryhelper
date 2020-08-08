package com.example.solitaryhelper.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.ActivityAppGuideBinding

class AppGuideActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityAppGuideBinding>(this,R.layout.activity_app_guide)
    }
}