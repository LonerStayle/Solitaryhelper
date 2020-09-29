package com.example.solitaryhelper.database.localdb.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.solitaryhelper.database.localdb.entitiy.KaKaoTalkData

@Dao
interface KaKaoDao {

    @Query("SELECT * FROM KaKaoTalkData")
    fun getAllList(): LiveData<List<KaKaoTalkData>>

    @Insert
    fun insert(kaoTalkData: KaKaoTalkData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllList(kaoTalkData: List<KaKaoTalkData>)

}