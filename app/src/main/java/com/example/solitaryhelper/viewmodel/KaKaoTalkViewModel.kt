package com.example.solitaryhelper.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import com.example.solitaryhelper.database.localdb.dao.KaKaoDao
import com.example.solitaryhelper.view.contents.Contents
import com.example.solitaryhelper.database.localdb.entitiy.KaKaoTalkData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*


class KaKaoTalkViewModel(private val dataSource: KaKaoDao) : ViewModel() {
    val ioScope = CoroutineScope(Dispatchers.IO + Job())
    val kakaoData: LiveData<List<KaKaoTalkData>>
        get() = dataSource.getAllList()


    fun allListInsert(kaoTalkData: List<KaKaoTalkData>) {
        ioScope.launch {
            dataSource.insertAllList(kaoTalkData)
        }
    }
    fun insert(kaoTalkData: KaKaoTalkData){
        ioScope.launch {
            dataSource.insert(kaoTalkData)
        }
    }





    @SuppressLint("SimpleDateFormat")
    fun setTimeList(dataList: Array<Array<String>>): Array<Array<String>> {

         val timeChatListPosition:MutableList<MutableList<String>>?
        var newTimeUp = 30000000L
        val newTimeDown = (Math.random() * (100000 - 40000) + 40000).toLong()

        timeChatListPosition = dataList.map { it.toMutableList() }.toMutableList()

        for (row in dataList.size-1 downTo 0) {
            for (col in dataList[row].indices) {
                timeChatListPosition[row][col] = Contents.timePattern.format(Date(System.currentTimeMillis() - newTimeUp))
                newTimeUp -= newTimeDown

            }
        }

        return timeChatListPosition.map { it.toTypedArray() }.toTypedArray()

    }

}

