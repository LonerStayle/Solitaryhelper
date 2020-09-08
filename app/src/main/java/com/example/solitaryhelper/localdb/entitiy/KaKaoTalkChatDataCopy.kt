package com.example.solitaryhelper.localdb.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters


data class KaKaoTalkChatDataCopy(
    var textList: String,
    val user: Boolean,
    val timeList:String
)

