package com.example.solitaryhelper.localdb.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity
data class KaKaoTalkChatData(
    @PrimaryKey
    val id:Long = 0L,
    @TypeConverters(ConverterKaKaoTalkData::class)
    var textList: List<String>,
    @TypeConverters(ConverterKaKaoChatDataUser::class)
    val user: MutableList<Boolean>?,
    @TypeConverters(ConverterKaKaoTalkData::class)
    val timeList:List<String>
)

