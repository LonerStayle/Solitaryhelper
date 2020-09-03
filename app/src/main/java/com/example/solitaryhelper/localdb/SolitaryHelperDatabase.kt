package com.example.solitaryhelper.localdb

import android.content.Context
import androidx.room.*
import com.example.solitaryhelper.localdb.dao.KaKaoChatDao
import com.example.solitaryhelper.localdb.dao.KaKaoDao
import com.example.solitaryhelper.localdb.dao.SmsDao
import com.example.solitaryhelper.localdb.dao.UserDao
import com.example.solitaryhelper.localdb.entitiy.*


@Database(entities = [UserProfile::class,Sms::class,KaKaoTalkData::class,KaKaoTalkChatData::class], exportSchema = false, version = 1)
@TypeConverters(ConverterKaKaoTalkData::class,ConverterKaKaoChatDataUser::class)
abstract class SolitaryHelperDatabase : RoomDatabase() {
    abstract val userDataSource:UserDao
    abstract val smsDataSource:SmsDao
    abstract val kaKaoDataSource:KaKaoDao
    abstract val kakaoChatDataSource:KaKaoChatDao

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
