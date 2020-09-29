package com.example.solitaryhelper.repository

import androidx.lifecycle.LiveData
import com.example.solitaryhelper.database.localdb.dao.SmsDao
import com.example.solitaryhelper.database.localdb.entitiy.Sms

interface SmsDataSource{

    fun getSmsList(): LiveData<List<Sms>>

    fun insertSms(sms: Sms)

    fun insertSmsList(smsList:List<Sms>)

    fun deleteSms(sms: Sms)

    fun deleteSmsList(smsList: List<Sms>)
}

class SmsRepository(private val dataSource:SmsDao):SmsDataSource{
    override fun getSmsList(): LiveData<List<Sms>> {
        return dataSource.getSmsList()
    }

    override fun insertSms(sms: Sms) {
        dataSource.insertSms(sms)
    }

    override fun insertSmsList(smsList: List<Sms>) {
        dataSource.insertSmsList(smsList)
    }

    override fun deleteSms(sms: Sms) {
        dataSource.deleteSms(sms)
    }

    override fun deleteSmsList(smsList: List<Sms>) {
        dataSource.deleteSmsList(smsList)
    }

}