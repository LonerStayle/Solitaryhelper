package com.example.solitaryhelper.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.solitaryhelper.view.dataclass.KaKaoTalkData


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

