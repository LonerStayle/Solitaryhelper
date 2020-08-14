package com.example.solitaryhelper.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import androidx.lifecycle.ViewModel
import com.example.solitaryhelper.localdb.dao.RoomDao
import com.example.solitaryhelper.localdb.entitiy.KaKaoTalkChatData
import kotlinx.coroutines.launch


class KaKaoChatViewModel(private val dao:RoomDao) : ViewModel() {

    private val ioScope = CoroutineScope(Dispatchers.IO)
    val myChatText: LiveData<List<KaKaoTalkChatData>>
        get() = dao.getAllList()

    fun insertAllList(myChatList:List<KaKaoTalkChatData>){
        ioScope.launch {
           dao.insertAllList(myChatList)
       }
    }
    fun insertNewMyChat(myChat:KaKaoTalkChatData){
        ioScope.launch {
            dao.insertNewMyChat(myChat)
        }
    }


}

