package com.example.solitaryhelper.localdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.solitaryhelper.localdb.dao.RoomDao
import com.example.solitaryhelper.localdb.entitiy.UserProfile


@Database(entities = [UserProfile::class], exportSchema = false, version = 1)
abstract class SolitaryHelperDatabase : RoomDatabase() {
    abstract val dataSource: RoomDao

    companion object {
        @Volatile
        private var INSTANCE: SolitaryHelperDatabase? = null
        fun getInstance(context: Context): SolitaryHelperDatabase = synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(
                context,
                SolitaryHelperDatabase::class.java,
                "SolitaryHelperDatabase1"
            ).fallbackToDestructiveMigration()
                .build().also {
                    INSTANCE = it
                }
        }
    }
}
