package com.example.solitaryhelper.localdb

import android.content.Context
import androidx.room.*
import com.example.solitaryhelper.localdb.dao.KaKaoDao
import com.example.solitaryhelper.localdb.dao.SmsDao
import com.example.solitaryhelper.localdb.dao.UserDao
import com.example.solitaryhelper.localdb.entitiy.*


@Database(entities = [UserProfile::class,Sms::class,KaKaoTalkData::class], exportSchema = false, version = 1)
@TypeConverters(ConverterKaKaoTalkData::class)
abstract class SolitaryHelperDatabase : RoomDatabase() {
    abstract val userDataSource:UserDao
    abstract val smsDataSource:SmsDao
    abstract val kaKaoDataSource:KaKaoDao

    companion object {
        @Volatile
        private var INSTANCE: SolitaryHelperDatabase? = null
        fun getInstance(context: Context): SolitaryHelperDatabase = synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(
                context,
                SolitaryHelperDatabase::class.java,
                "SolitaryHelper_Database"
            ).fallbackToDestructiveMigration()
                .build().also {
                    INSTANCE = it
                }
        }
    }
}
