package com.example.solitaryhelper.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.solitaryhelper.R
import com.example.solitaryhelper.view.pref.PrefCheckRun
import kotlinx.android.synthetic.main.activity_autochatrun.*

class AutoChatRunCheckActiviy:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_autochatrun)

        button_yes.setOnClickListener {
            autoChatRun = false
            finish()
        }

        button_no.setOnClickListener {
            finish()
        }

    }
}