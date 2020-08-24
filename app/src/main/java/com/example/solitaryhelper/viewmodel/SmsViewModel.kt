package com.example.solitaryhelper.viewmodel

import androidx.lifecycle.*
import androidx.room.Dao
import com.example.solitaryhelper.localdb.dao.RoomDao
import com.example.solitaryhelper.localdb.entitiy.Sms
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SmsViewModel(private val dataSource: RoomDao) : ViewModel() {
    val ioScope = CoroutineScope(Dispatchers.IO + Job())
    val SmsList: LiveData<List<Sms>>
        get() = dataSource.getSmsList()

    fun smsInsert(text: String, boolean: Boolean, time: String) {
        ioScope.launch {
            dataSource.insertSms(Sms(textMessage = text, mytext = boolean, timeBar = time))
        }

    }
    fun smsDelete(sms: Sms){
        ioScope.launch {
            dataSource.deleteSms(sms)
        }
    }

}