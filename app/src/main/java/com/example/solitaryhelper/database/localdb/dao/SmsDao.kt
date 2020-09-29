package com.example.solitaryhelper.database.localdb.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.solitaryhelper.database.localdb.entitiy.Sms


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