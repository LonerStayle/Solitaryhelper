package com.example.solitaryhelper.database.localdb.entitiy

import androidx.room.TypeConverter
import com.google.gson.Gson

class ConverterKaKaoTalkData {

    @TypeConverter
    fun listtoJson(value: List<String>) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) =
        Gson().fromJson(value, Array<String>::class.java).toList()


}