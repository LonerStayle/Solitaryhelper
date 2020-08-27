package com.example.solitaryhelper.view.utill

fun toPicTextControl(text:String):String {
    text.replace("</b>".toRegex(),"")
    text.replace("<b>".toRegex(),"")

    return text
}