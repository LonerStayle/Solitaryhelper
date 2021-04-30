package com.example.solitaryhelper.database.localdb

import android.content.Context
import android.os.Parcelable
import androidx.room.*
import com.example.solitaryhelper.database.api.naver.dataholder.NaverBlog
import com.example.solitaryhelper.database.localdb.dao.KaKaoChatDao
import com.example.solitaryhelper.database.localdb.dao.KaKaoDao
import com.example.solitaryhelper.database.localdb.dao.SmsDao
import com.example.solitaryhelper.database.localdb.dao.UserDao
import com.example.solitaryhelper.database.localdb.entitiy.*
import com.example.solitaryhelper.database.localdb.entitiy.KaKaoTalkChatData
import kotlinx.android.parcel.Parcelize


@Database(
    entities = [UserProfile::class, Sms::class, KaKaoTalkData::class,
        KaKaoTalkChatData::class,
        KaKaoTalkChatData1::class, KaKaoTalkChatData2::class, KaKaoTalkChatData3::class,
        KaKaoTalkChatData4::class, KaKaoTalkChatData5::class, KaKaoTalkChatData6::class,
        KaKaoTalkChatData7::class, KaKaoTalkChatData8::class, KaKaoTalkChatData9::class,
        KaKaoTalkChatData10::class, KaKaoTalkChatData11::class, KaKaoTalkChatData12::class,
        KaKaoTalkChatData13::class, KaKaoTalkChatData14::class, KaKaoTalkChatData15::class,
        KaKaoTalkChatData16::class, KaKaoTalkChatData17::class, KaKaoTalkChatData18::class,
        KaKaoTalkChatData19::class], exportSchema = false, version = 1
)

@TypeConverters(ConverterKaKaoTalkData::class, ConverterKaKaoChatDataUser::class)
abstract class SolitaryHelperDatabase : RoomDatabase() {
    abstract val userDataSource: UserDao
    abstract val smsDataSource: SmsDao
    abstract val kaKaoDataSource: KaKaoDao
    abstract val kakaoChatDataSource: KaKaoChatDao

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






