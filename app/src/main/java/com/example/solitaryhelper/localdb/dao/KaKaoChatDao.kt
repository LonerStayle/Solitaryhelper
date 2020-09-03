package com.example.solitaryhelper.localdb.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.solitaryhelper.localdb.entitiy.KaKaoTalkChatData

@Dao
interface KaKaoChatDao {

    @Query("SELECT * FROM KaKaoTalkChatData")
    fun getAllList():LiveData<List<KaKaoTalkChatData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(kaoTalkChatData: KaKaoTalkChatData)

    @Insert
    fun allInsert(kaoTalkChatData: List<KaKaoTalkChatData>)
}