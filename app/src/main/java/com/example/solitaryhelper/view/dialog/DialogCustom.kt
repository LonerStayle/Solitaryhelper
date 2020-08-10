package com.example.solitaryhelper.view.dialog

import android.app.Dialog
import android.content.Context
import androidx.databinding.DataBindingUtil
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.HeaderNavigationMainBinding
import kotlinx.android.synthetic.main.dialog_main_id_create.*
import kotlinx.android.synthetic.main.header_navigation_main.*

class DialogCustom (context: Context) {
    val dialogCreate = Dialog(context, R.style.Theme_AppCompat_Light_Dialog_Alert)

    fun dialogMainIdCreate() {
        dialogCreate.setContentView(R.layout.dialog_main_id_create)
    }

}

