package com.example.solitaryhelper.localdb.dao

import android.service.autofill.UserData
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.solitaryhelper.localdb.entitiy.Sms
import com.example.solitaryhelper.localdb.entitiy.UserProfile


@Dao
interface SmsDao {


    @Query("SELECT * FROM Sms")
    fun getSmsList():LiveData<List<Sms>>

    @Insert
    fun insertSms(sms: Sms)

    @Insert
    fun insertSmsList(smsList:List<Sms>)

    @Delete
    fun deleteSms(sms: Sms)

    @Delete
    fun deleteSmsList(smsList: List<Sms>)





}