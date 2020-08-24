package com.example.solitaryhelper.localdb.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Sms(
    @PrimaryKey(autoGenerate = true)
    val id:Long=0L,
    val textMessage: String,
    val mytext: Boolean,
    val timeBar:String
)