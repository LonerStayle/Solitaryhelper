package com.example.solitaryhelper.database.localdb.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters


@Entity
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

)


//data class KaKaoTalkData(
//
//    val id:Long,
//    val name:String,
//    val image:String,
//    val textBoxList: MutableList<Array<String>>,
//    var itemLastText: String,
//    var chatNotification: Int,
//    var messageArrivalTime: Array<Array<String>>?,
//    var visibleSettingList: Int?,
//    var itemTimeLast:String?
//) {
//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (javaClass != other?.javaClass) return false
//
//        other as KaKaoTalkData
//
//        if (id != other.id) return false
//        if (name != other.name) return false
//        if (image != other.image) return false
//        if (textBoxList != other.textBoxList) return false
//        if (itemLastText != other.itemLastText) return false
//        if (chatNotification != other.chatNotification) return false
//        if (messageArrivalTime != null) {
//            if (other.messageArrivalTime == null) return false
//            if (!messageArrivalTime!!.contentDeepEquals(other.messageArrivalTime!!)) return false
//        } else if (other.messageArrivalTime != null) return false
//        if (visibleSettingList != other.visibleSettingList) return false
//        if (itemTimeLast != other.itemTimeLast) return false
//
//        return true
//    }
//
//    override fun hashCode(): Int {
//        var result = id.hashCode()
//        result = 31 * result + name.hashCode()
//        result = 31 * result + image.hashCode()
//        result = 31 * result + textBoxList.hashCode()
//        result = 31 * result + itemLastText.hashCode()
//        result = 31 * result + chatNotification
//        result = 31 * result + (messageArrivalTime?.contentDeepHashCode() ?: 0)
//        result = 31 * result + (visibleSettingList ?: 0)
//        result = 31 * result + (itemTimeLast?.hashCode() ?: 0)
//        return result
//    }
//}
