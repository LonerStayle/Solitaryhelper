package com.example.solitaryhelper.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
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
    }

     fun setButtonPrevClickListener(v: View) {
            textView_wiseList.text  = if (stack.isEmpty())
                wiseList[Random().nextInt(wiseList.size)]
            else
                stack.pop()
    }

    private fun setWiseText() {
        textView_wiseList.text  = wiseList[Random().nextInt(wiseList.size)]
    }

     fun setButtonNextClickListener(v:View) {
            stack.push(textView_wiseList.text.toString())
            textView_wiseList.text = wiseList[Random().nextInt(wiseList.size)]

    }

     fun setButtonGototheMainActivity(v:View){
        startActivity(Intent(this,MainActivity::class.java))
    }


}