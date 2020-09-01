package com.example.solitaryhelper.localdb.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.lifecycle.LiveData
import androidx.room.Insert
import com.example.solitaryhelper.localdb.entitiy.KaKaoTalkData

@Dao
interface KaKaoDao {

    @Query("SELECT * FROM KaKaoTalkData")
    fun getAllList(): LiveData<List<KaKaoTalkData>>

    @Insert
    fun insert(kaoTalkData: KaKaoTalkData)

    @Insert
    fun insertAllList(kaoTalkData: List<KaKaoTalkData>)
}