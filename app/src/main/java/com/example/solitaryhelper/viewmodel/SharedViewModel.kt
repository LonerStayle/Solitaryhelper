package com.example.solitaryhelper.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import androidx.lifecycle.ViewModel
import com.example.solitaryhelper.localdb.data.KaKaoTalkChatData
import com.example.solitaryhelper.localdb.data.KaKaoTalkData


class SharedViewModel : ViewModel() {

    val firstRunKaKaoTalkClass = MutableLiveData<MutableList<KaKaoTalkData>>()
    val autoChatPosition = MutableLiveData<Int>()

    fun firstRunKaKaoTalkSetting(test: MutableList<KaKaoTalkData>) {
        firstRunKaKaoTalkClass.postValue(test)
    }

    fun runAutoChat(position: Int) {
        autoChatPosition.postValue(position)
    }

}

