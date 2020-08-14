package com.example.solitaryhelper.localdb.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class KaKaoTalkChatData (

    @PrimaryKey(autoGenerate = true)
    val id:Long = 0L ,

    val textList:String,
    val user:Boolean?

)