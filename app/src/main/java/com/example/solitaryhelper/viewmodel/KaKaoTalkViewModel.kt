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
    private var timeChatListPosition5: Array<String>? = null
    private var timeChatListPosition6: Array<String>? = null
    private var timeChatListPosition7: Array<String>? = null
    private var timeChatListPosition8: Array<String>? = null
    private var timeChatListPosition9: Array<String>? = null
    private var timeChatListPosition10: Array<String>? = null
    private var timeChatListPosition11: Array<String>? = null
    private var timeChatListPosition12: Array<String>? = null
    private var timeChatListPosition13: Array<String>? = null
    private var timeChatListPosition14: Array<String>? = null
    private var timeChatListPosition15: Array<String>? = null
    private var timeChatListPosition16: Array<String>? = null
    private var timeChatListPosition17: Array<String>? = null
    private var timeChatListPosition18: Array<String>? = null
    private var timeChatListPosition19: Array<String>? = null


    @SuppressLint("SimpleDateFormat")
    fun setTimeList(dataList: List<KaKaoTalkData>): Array<Array<String>> {
        var timeAllList: Array<Array<String>>

        timeChatListPosition0 = Array(dataList[0].textBoxList.size) { "" }
        timeChatListPosition1 = Array(dataList[1].textBoxList.size) { "" }
        timeChatListPosition2 = Array(dataList[2].textBoxList.size) { "" }
        timeChatListPosition3 = Array(dataList[3].textBoxList.size) { "" }
        timeChatListPosition4 = Array(dataList[4].textBoxList.size) { "" }
        timeChatListPosition5 = Array(dataList[5].textBoxList.size) { "" }
        timeChatListPosition6 = Array(dataList[6].textBoxList.size) { "" }
        timeChatListPosition7 = Array(dataList[7].textBoxList.size) { "" }
        timeChatListPosition8 = Array(dataList[8].textBoxList.size) { "" }
        timeChatListPosition9 = Array(dataList[9].textBoxList.size) { "" }
        timeChatListPosition10 = Array(dataList[10].textBoxList.size) { "" }
        timeChatListPosition11 = Array(dataList[11].textBoxList.size) { "" }
        timeChatListPosition12 = Array(dataList[12].textBoxList.size) { "" }
        timeChatListPosition13 = Array(dataList[13].textBoxList.size) { "" }
        timeChatListPosition14 = Array(dataList[14].textBoxList.size) { "" }
        timeChatListPosition15 = Array(dataList[15].textBoxList.size) { "" }
        timeChatListPosition16 = Array(dataList[16].textBoxList.size) { "" }
        timeChatListPosition17 = Array(dataList[17].textBoxList.size) { "" }
        timeChatListPosition18 = Array(dataList[18].textBoxList.size) { "" }
        timeChatListPosition19 = Array(dataList[19].textBoxList.size) { "" }


        var oldTimeUp = 6000000L
        val oldTimeDown = (Math.random() * (300000 - 50000) + 50000).toLong()
        var newTimeUp = 2000000L
        val newTimeDown = (Math.random() * (80000 - 40000) + 40000).toLong()


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






        for (i in dataList[5].textBoxList.indices) {
            newTimeUp = -newTimeDown
            timeChatListPosition5!![i] += Contents.timePattern.format(Date(System.currentTimeMillis() - newTimeUp))
        }
        for (i in dataList[6].textBoxList.indices) {
            oldTimeUp = -oldTimeDown
            timeChatListPosition6!![i] += Contents.timePattern.format(Date(System.currentTimeMillis() - oldTimeUp))
        }
        for (i in dataList[7].textBoxList.indices) {
            oldTimeUp -= oldTimeDown
            timeChatListPosition7!![i] += Contents.timePattern.format(Date(System.currentTimeMillis() - oldTimeUp))
        }
        for (i in dataList[8].textBoxList.indices) {
            oldTimeUp -= oldTimeDown
            timeChatListPosition8!![i] += Contents.timePattern.format(Date(System.currentTimeMillis() - oldTimeUp))
        }
        for (i in dataList[9].textBoxList.indices) {
            oldTimeUp -= oldTimeDown
            timeChatListPosition9!![i] += Contents.timePattern.format(Date(System.currentTimeMillis() - oldTimeUp))
        }
        for (i in dataList[10].textBoxList.indices) {
            oldTimeUp -= oldTimeDown
            timeChatListPosition10!![i] += Contents.timePattern.format(Date(System.currentTimeMillis() - oldTimeUp))
        }
        for (i in dataList[11].textBoxList.indices) {
            oldTimeUp -= oldTimeDown
            timeChatListPosition11!![i] += Contents.timePattern.format(Date(System.currentTimeMillis() - oldTimeUp))
        }
        for (i in dataList[12].textBoxList.indices) {
            oldTimeUp -= oldTimeDown
            timeChatListPosition12!![i] += Contents.timePattern.format(Date(System.currentTimeMillis() - oldTimeUp))
        }
        for (i in dataList[13].textBoxList.indices) {
            oldTimeUp -= oldTimeDown
            timeChatListPosition13!![i] += Contents.timePattern.format(Date(System.currentTimeMillis() - oldTimeUp))
        }
        for (i in dataList[14].textBoxList.indices) {
            oldTimeUp -= oldTimeDown
            timeChatListPosition14!![i] += Contents.timePattern.format(Date(System.currentTimeMillis() - oldTimeUp))
        }
        for (i in dataList[15].textBoxList.indices) {
            oldTimeUp -= oldTimeDown
            timeChatListPosition15!![i] += Contents.timePattern.format(Date(System.currentTimeMillis() - oldTimeUp))
        }
        for (i in dataList[16].textBoxList.indices) {
            oldTimeUp -= oldTimeDown
            timeChatListPosition16!![i] += Contents.timePattern.format(Date(System.currentTimeMillis() - oldTimeUp))
        }
        for (i in dataList[17].textBoxList.indices) {
            oldTimeUp -= oldTimeDown
            timeChatListPosition17!![i] += Contents.timePattern.format(Date(System.currentTimeMillis() - oldTimeUp))
        }
        for (i in dataList[18].textBoxList.indices) {
            oldTimeUp -= oldTimeDown
            timeChatListPosition18!![i] += Contents.timePattern.format(Date(System.currentTimeMillis() - oldTimeUp))
        }
        for (i in dataList[19].textBoxList.indices) {
            oldTimeUp -= oldTimeDown
            timeChatListPosition19!![i] += Contents.timePattern.format(Date(System.currentTimeMillis() - oldTimeUp))
        }


        timeAllList = arrayOf(
            timeChatListPosition19!!,
            timeChatListPosition18!!,
            timeChatListPosition17!!,
            timeChatListPosition16!!,
            timeChatListPosition15!!,
            timeChatListPosition14!!,
            timeChatListPosition13!!,
            timeChatListPosition12!!,
            timeChatListPosition11!!,
            timeChatListPosition10!!,
            timeChatListPosition9!!,
            timeChatListPosition8!!,
            timeChatListPosition7!!,
            timeChatListPosition6!!,
            timeChatListPosition5!!,
            timeChatListPosition4!!,
            timeChatListPosition3!!,
            timeChatListPosition2!!,
            timeChatListPosition1!!,
            timeChatListPosition0!!
        )
        return timeAllList
    }

}

