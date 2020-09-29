package com.example.solitaryhelper.viewmodel

import androidx.lifecycle.*
import com.example.solitaryhelper.database.localdb.dao.SmsDao
import com.example.solitaryhelper.database.localdb.entitiy.Sms
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SmsViewModel(private val dataSource: SmsDao) : ViewModel() {
    val ioScope = CoroutineScope(Dispatchers.IO + Job())
    val SmsList: LiveData<List<Sms>>
        get() = dataSource.getSmsList()

    fun smsInsert(text: String, boolean: Boolean, time: String) {
        ioScope.launch {
            dataSource.insertSms(Sms(textMessage = text, mytext = boolean, timeBar = time))
        }

    }
    fun smsAllDelete(sms: List<Sms>){
        ioScope.launch {
            dataSource.deleteSmsList(sms)
        }
    }

}