package com.example.solitaryhelper.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.solitaryhelper.view.contents.Contents
import com.example.solitaryhelper.view.dataclass.KaKaoTalkData
import java.text.FieldPosition
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random


class KaKaoTalkViewModel : ViewModel() {

    private var timeChatListPosition0: Array<String>? = null
    private var timeChatListPosition1: Array<String>? = null
    private var timeChatListPosition2: Array<String>? = null
    private var timeChatListPosition3: Array<String>? = null
    private var timeChatListPosition4: Array<String>? = null
//    private var timeChatListPosition5: Array<String>? = null
//    private var timeChatListPosition6: Array<String>? = null
//    private var timeChatListPosition7: Array<String>? = null
//    private var timeChatListPosition8: Array<String>? = null
//    private var timeChatListPosition9: Array<String>? = null
//    private var timeChatListPosition10: Array<String>? = null
//    private var timeChatListPosition11: Array<String>? = null
//    private var timeChatListPosition12: Array<String>? = null
//    private var timeChatListPosition13: Array<String>? = null
//    private var timeChatListPosition14: Array<String>? = null
//    private var timeChatListPosition15: Array<String>? = null
//    private var timeChatListPosition16: Array<String>? = null
//    private var timeChatListPosition17: Array<String>? = null
//    private var timeChatListPosition18: Array<String>? = null
//    private var timeChatListPosition19: Array<String>? = null


    @SuppressLint("SimpleDateFormat")
    fun setTimeList(dataList: List<KaKaoTalkData>):Array<Array<String>> {
        var timeAllList: Array<Array<String>>

        timeChatListPosition1 = Array(dataList[1].textBoxList.size) {""}
        timeChatListPosition0 = Array(dataList[0].textBoxList.size) {""}
        timeChatListPosition2 = Array(dataList[2].textBoxList.size) {""}
        timeChatListPosition3 = Array(dataList[3].textBoxList.size) {""}
        timeChatListPosition4 = Array(dataList[4].textBoxList.size) {""}


        val oldTimeUp = 6000000L
        val oldTimeDown = (Math.random() * (400000 - 50000) + 50000).toLong()
        var newTimeUp = 300000L
        val newTimeDown = (Math.random() * (60000 - 40000) + 40000).toLong()


        for (i in dataList[0].textBoxList.indices) {
            newTimeUp -= newTimeDown
            timeChatListPosition0!![i] += Contents.timePattern.format(Date(System.currentTimeMillis() - newTimeUp))
        }

        for (i in dataList[1].textBoxList.indices) {
            newTimeUp -= newTimeDown
            timeChatListPosition1!![i] += Contents.timePattern.format(Date(System.currentTimeMillis() - newTimeUp))
        }
        for (i in dataList[2].textBoxList.indices) {
            newTimeUp -= newTimeDown
            timeChatListPosition2!![i] += Contents.timePattern.format(Date(System.currentTimeMillis() - newTimeUp))
        }
        for (i in dataList[3].textBoxList.indices) {
            newTimeUp -= newTimeDown
            timeChatListPosition3!![i] += Contents.timePattern.format(Date(System.currentTimeMillis() - newTimeUp))
        }

        for (i in dataList[4].textBoxList.indices) {
            newTimeUp -= newTimeDown
            timeChatListPosition4!![i] += SimpleDateFormat("aa hh:mm").format(Date(System.currentTimeMillis() - newTimeUp))
        }






//        for (i in dataList[5].textBoxList.indices) {
//            newTimeUp =- newTimeDown
//            timeChatListPosition5!![i] += Contents.timePattern.format(Date(System.currentTimeMillis() - newTimeUp))
//        }
//        for (i in dataList[6].textBoxList.indices) {
//            oldTimeUp =- oldTimeDown
//            timeChatListPosition6!![i] += Contents.timePattern.format(Date(System.currentTimeMillis() - oldTimeUp))
//        }
//        for (i in dataList[7].textBoxList.indices) {
//            oldTimeUp -= oldTimeDown
//            timeChatListPosition7!![i] += Contents.timePattern.format(Date(System.currentTimeMillis() - oldTimeUp))
//        }
//        for (i in dataList[8].textBoxList.indices) {
//            oldTimeUp -= oldTimeDown
//            timeChatListPosition8!![i] += Contents.timePattern.format(Date(System.currentTimeMillis() - oldTimeUp))
//        }
//        for (i in dataList[9].textBoxList.indices) {
//            oldTimeUp -= oldTimeDown
//            timeChatListPosition9!![i] += Contents.timePattern.format(Date(System.currentTimeMillis() - oldTimeUp))
//        }
//        for (i in dataList[10].textBoxList.indices) {
//            oldTimeUp -= oldTimeDown
//            timeChatListPosition10!![i] += Contents.timePattern.format(Date(System.currentTimeMillis() - oldTimeUp))
//        }
//        for (i in dataList[11].textBoxList.indices) {
//            oldTimeUp -= oldTimeDown
//            timeChatListPosition11!![i] += Contents.timePattern.format(Date(System.currentTimeMillis() - oldTimeUp))
//        }
//        for (i in dataList[12].textBoxList.indices) {
//            oldTimeUp -= oldTimeDown
//            timeChatListPosition12!![i] += Contents.timePattern.format(Date(System.currentTimeMillis() - oldTimeUp))
//        }
//        for (i in dataList[13].textBoxList.indices) {
//            oldTimeUp -= oldTimeDown
//            timeChatListPosition13!![i] += Contents.timePattern.format(Date(System.currentTimeMillis() - oldTimeUp))
//        }
//        for (i in dataList[14].textBoxList.indices) {
//            oldTimeUp -= oldTimeDown
//            timeChatListPosition14!![i] += Contents.timePattern.format(Date(System.currentTimeMillis() - oldTimeUp))
//        }
//        for (i in dataList[15].textBoxList.indices) {
//            oldTimeUp -= oldTimeDown
//            timeChatListPosition15!![i] += Contents.timePattern.format(Date(System.currentTimeMillis() - oldTimeUp))
//        }
//        for (i in dataList[16].textBoxList.indices) {
//            oldTimeUp -= oldTimeDown
//            timeChatListPosition16!![i] += Contents.timePattern.format(Date(System.currentTimeMillis() - oldTimeUp))
//        }
//        for (i in dataList[17].textBoxList.indices) {
//            oldTimeUp -= oldTimeDown
//            timeChatListPosition17!![i] += Contents.timePattern.format(Date(System.currentTimeMillis() - oldTimeUp))
//        }
//        for (i in dataList[18].textBoxList.indices) {
//            oldTimeUp -= oldTimeDown
//            timeChatListPosition18!![i] += Contents.timePattern.format(Date(System.currentTimeMillis() - oldTimeUp))
//        }
//        for (i in dataList[19].textBoxList.indices) {
//            oldTimeUp -= oldTimeDown
//            timeChatListPosition19!![i] += Contents.timePattern.format(Date(System.currentTimeMillis() - oldTimeUp))
//        }


         timeAllList = arrayOf(
            timeChatListPosition4!!,
            timeChatListPosition3!!,
            timeChatListPosition2!!,
            timeChatListPosition1!!,
            timeChatListPosition0!!
        )
        return timeAllList
    }

}

