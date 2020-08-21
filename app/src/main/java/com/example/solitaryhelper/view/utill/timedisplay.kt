package com.example.solitaryhelper.view.utill

fun timeDisplay(lastTime:String): String {

    if (lastTime[3] == '0') {
        return lastTime.removeRange(3..3)
    }
    return lastTime
}