package com.example.solitaryhelper.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.solitaryhelper.view.dataclass.KaKaoTalkData


class SharedViewModel : ViewModel() {

    data class SendToChange(
        val sendToPosition:Int,
        val sendToLastText:String,
        val sendToLastTime:String
    )

    val firstRunKaKaoTalkClass = MutableLiveData<MutableList<KaKaoTalkData>>()

    val sendToChanges = MutableLiveData<SendToChange>()

    val kaKaoChatTotalNotificationScore = MutableLiveData<Int>()

    fun firstRunKaKaoTalkSetting(test: MutableList<KaKaoTalkData>) {
        firstRunKaKaoTalkClass.postValue(test)
    }

    fun sendToChanges(sendToChange: SendToChange){
        sendToChanges.postValue(sendToChange)
    }

    fun kaKaoChatTotalNotificationScore(score:Int){
        kaKaoChatTotalNotificationScore.postValue(score)
    }


}

