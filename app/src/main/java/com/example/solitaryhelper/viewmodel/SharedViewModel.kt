package com.example.solitaryhelper.viewmodel

import android.util.SparseBooleanArray
import android.util.SparseIntArray
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import androidx.lifecycle.ViewModel
import com.example.solitaryhelper.localdb.data.KaKaoTalkChatData
import com.example.solitaryhelper.localdb.data.KaKaoTalkData


class SharedViewModel : ViewModel() {
    data class ZeroPositionCheck(
        val currentIdPosition: Int,
        val positionZeroCheck:Boolean
    )

    val firstRunKaKaoTalkClass = MutableLiveData<MutableList<KaKaoTalkData>>()

    val sendToPosition = MutableLiveData<Int>()


    fun firstRunKaKaoTalkSetting(test: MutableList<KaKaoTalkData>) {
        firstRunKaKaoTalkClass.postValue(test)
    }

    fun sendToPosition(int:Int){
        sendToPosition.postValue(int)
    }



}

