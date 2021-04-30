package com.example.solitaryhelper.repository

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.solitaryhelper.database.localdb.dao.KaKaoDao
import com.example.solitaryhelper.database.localdb.entitiy.KaKaoTalkData

interface KaKaoTalkDataSource{

    fun getAllList(): LiveData<List<KaKaoTalkData>>

    fun insert(kaoTalkData: KaKaoTalkData)

    fun insertAllList(kaoTalkData: List<KaKaoTalkData>)

}


class KaKaoTalkRepository(private val dataSource:KaKaoDao):KaKaoTalkDataSource {
    override fun getAllList(): LiveData<List<KaKaoTalkData>> {
       return dataSource.getAllList()
    }

    override fun insert(kaoTalkData: KaKaoTalkData) {
        dataSource.insert(kaoTalkData)
    }

    override fun insertAllList(kaoTalkData: List<KaKaoTalkData>) {
        dataSource.insertAllList(kaoTalkData)
    }

}

