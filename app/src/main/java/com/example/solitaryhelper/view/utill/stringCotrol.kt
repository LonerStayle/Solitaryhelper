package com.example.solitaryhelper.view.utill

fun toPicTextControl(text: String): String {
    val realFinal:String
    val final: String
    val newTest: String = text.replace("<b>".toRegex(), "")
    final = newTest.replace("</b>".toRegex(), "")
    realFinal = final.replace("&quot;".toRegex(),"")
    return realFinal
}