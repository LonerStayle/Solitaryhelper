package com.example.solitaryhelper.view.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<VDB : ViewDataBinding>(@LayoutRes val resId: Int) :
    AppCompatActivity() {

    lateinit var binding: VDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, resId)
        binding.apply {
            setData()
            setClickEventListener()
        }
    }

    abstract fun VDB.setData()
    open fun VDB.setClickEventListener() = Unit
}