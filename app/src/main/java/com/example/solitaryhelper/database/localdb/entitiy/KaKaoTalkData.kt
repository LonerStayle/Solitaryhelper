package com.example.solitaryhelper.database.localdb.entitiy

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kotlinx.android.parcel.Parcelize


@Entity
@Parcelize
data class KaKaoTalkData(
    val id:Long,
    @PrimaryKey
    val name:String,
    val image:String,
    @TypeConverters(ConverterKaKaoTalkData::class)
    val textBoxList: List<String>,
    var itemLastText: String,
    var chatNotification: Int,

    @TypeConverters(ConverterKaKaoTalkData::class)
    var messageArrivalTime: List<String>?,
    var visibleSettingList: Int?,
    var itemTimeLast:String?

):Parcelable


