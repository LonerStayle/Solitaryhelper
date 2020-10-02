package com.example.solitaryhelper.view.dialog

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import androidx.databinding.DataBindingUtil
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.DialogMainIdCreateBinding
import com.example.solitaryhelper.databinding.HeaderNavigationMainBinding
import kotlinx.android.synthetic.main.dialog_main_id_create.*
import kotlinx.android.synthetic.main.header_navigation_main.*

class DialogCustom(context: Context,@LayoutRes layout:Int,@StyleRes style:Int) {
    val dialogCreate = Dialog(context, style)

    val dialogCustomCreateBinding: DialogMainIdCreateBinding = DataBindingUtil.inflate(
        LayoutInflater.from(
            context
        ), layout, null, false
    )

    fun dialogViewCreate() {
        dialogCreate.setContentView(dialogCustomCreateBinding.root)
    }

}

