package com.example.solitaryhelper.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentWiseSayingBinding
import kotlinx.android.synthetic.main.activity_wisesaying.*
import java.util.*

class WiseSayingActivity :AppCompatActivity(){
    private val wiseList by lazy {
        resources.getStringArray(R.array.wiseList)
    }
    private val stack = Stack<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wisesaying)

        setWiseText()
        buttonNextClickListener()
        buttonPrevClickListener()

    }

    private fun buttonPrevClickListener() {
        button_priv.setOnClickListener {

            textView_wiseList.text  = if (stack.isEmpty())
                wiseList[Random().nextInt(wiseList.size)]
            else
                stack.pop()

        }

    }

    private fun setWiseText() {
        textView_wiseList.text  = wiseList[Random().nextInt(wiseList.size)]
    }

    private fun buttonNextClickListener() {
        button_next.setOnClickListener {
            stack.push(textView_wiseList.text.toString())
            textView_wiseList.text = wiseList[Random().nextInt(wiseList.size)]
        }

    }
}