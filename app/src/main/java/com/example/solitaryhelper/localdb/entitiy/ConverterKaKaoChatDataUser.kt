package com.example.solitaryhelper.localdb.entitiy

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson

class ConverterKaKaoChatDataUser {
    @TypeConverter
    fun fromlistToBooleanJson(value: List<Boolean>) = Gson().toJson(value)

    @TypeConverter
    fun jsonToBooleanList(value: String) =
        Gson().fromJson(value, Array<Boolean>::class.java).toMutableList()

}