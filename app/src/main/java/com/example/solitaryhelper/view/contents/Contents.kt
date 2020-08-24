package com.example.solitaryhelper.view.contents

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

object Contents {
    const val MAIN_FIRST_ITEM = 0
    const val MAIN_SECOND_ITEM = 1
    const val MAIN_THIRD_ITEM = 2
    const val MAIN_FORCE_ITEM = 3
    const val MAIN_FIFTH_ITEM = 4
    const val MAIN_TAB_LAYOUT_TOTAL_ICON_SIZE = 5

    const val CALL_DELAY_NOTICATION_ENABLED_ON = 1
    const val CALL_DELAY_NOTICATION_ENABLED_OFF = 0


    @SuppressLint("SimpleDateFormat")
    val timePattern =SimpleDateFormat("aa hh:mm")

    const val IMAGE_URL_DEFAULT_FILE_PATH = "android.resource://com.example.solitaryhelper/"

     var AUTO_CHAT_DEALY = (Math.random() * (20000 - 3000) + 3000).toLong()

    var kaKaoTalkAdapterFirstRunMark = false
}