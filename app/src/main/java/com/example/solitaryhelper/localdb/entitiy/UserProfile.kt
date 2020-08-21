package com.example.solitaryhelper.localdb.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserProfile (
    @PrimaryKey
    val Id:Long,
    val userId:String,
    val darkPower:Int?,
    val profileImage:String?
)