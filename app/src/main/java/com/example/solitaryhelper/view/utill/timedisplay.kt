package com.example.solitaryhelper.view.utill

fun kakaoTimeDisplay(lastTime:String): String {

    if (lastTime[3] == '0') {
        return lastTime.removeRange(3..3)
    }
    return lastTime
}

fun topicTimeDisplay(time:String):String{
    if (time[6] == '0') {
        return time.removeRange(6..6)
    }
    return time
}