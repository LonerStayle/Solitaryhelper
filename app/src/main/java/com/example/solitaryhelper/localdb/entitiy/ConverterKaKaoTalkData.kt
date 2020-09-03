package com.example.solitaryhelper.localdb.entitiy

import androidx.room.Embedded
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ConverterKaKaoTalkData {

    @TypeConverter
    fun listtoJson(value: List<String>) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) =
        Gson().fromJson(value, Array<String>::class.java).toList()


}