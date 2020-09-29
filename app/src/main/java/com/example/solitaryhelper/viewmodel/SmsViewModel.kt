package com.example.solitaryhelper.viewmodel

import androidx.lifecycle.*
import com.example.solitaryhelper.database.localdb.dao.SmsDao
import com.example.solitaryhelper.database.localdb.entitiy.Sms
import com.example.solitaryhelper.repository.SmsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SmsViewModel(private val repository: SmsRepository) : ViewModel() {
    val ioScope = CoroutineScope(Dispatchers.IO + Job())
    val SmsList: LiveData<List<Sms>>
        get() = repository.getSmsList()

    fun smsInsert(text: String, boolean: Boolean, time: String) {
        ioScope.launch {
            repository.insertSms(Sms(textMessage = text, mytext = boolean, timeBar = time))
        }

    }
    fun smsAllDelete(sms: List<Sms>){
        ioScope.launch {
            repository.deleteSmsList(sms)
        }
    }

}