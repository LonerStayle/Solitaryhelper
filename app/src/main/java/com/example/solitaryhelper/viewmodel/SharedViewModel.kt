package com.example.solitaryhelper.viewmodel

import android.app.Notification
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.solitaryhelper.localdb.entitiy.KaKaoTalkData


class SharedViewModel : ViewModel() {

    data class SendToChange(
        val sendToPosition:Int,
        val sendToLastText:String,
        val sendToLastTime:String
    )

    val sendToChanges = MutableLiveData<SendToChange>()

    val kaKaoChatTotalNotificationScore = MutableLiveData<Int>()


    fun sendToChanges(sendToChange: SendToChange){
        sendToChanges.postValue(sendToChange)
    }

    fun kaKaoChatTotalNotificationScore(score:Int){
        kaKaoChatTotalNotificationScore.postValue(score)
    }


}

