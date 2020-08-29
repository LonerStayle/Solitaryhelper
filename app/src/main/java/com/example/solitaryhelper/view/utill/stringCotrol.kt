package com.example.solitaryhelper.view.utill

fun toPicTextControl(text: String): String {
    val final: String
    val newTest: String = text.replace("<b>".toRegex(), "")
    final = newTest.replace("</b>".toRegex(), "")
    return final
}