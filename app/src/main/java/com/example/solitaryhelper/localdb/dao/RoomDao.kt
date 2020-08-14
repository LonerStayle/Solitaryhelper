package com.example.solitaryhelper.localdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.lifecycle.LiveData
import com.example.solitaryhelper.localdb.entitiy.KaKaoTalkChatData


@Dao
interface RoomDao {
    @Query("SELECT * FROM KaKaoTalkChatData")
    fun getAllList():LiveData<List<KaKaoTalkChatData>>

    @Insert
    fun insertAllList(kaKaoTalkChatData: List<KaKaoTalkChatData>)

    @Insert
    fun insertNewMyChat(kaKaoTalkChatData: KaKaoTalkChatData)
}