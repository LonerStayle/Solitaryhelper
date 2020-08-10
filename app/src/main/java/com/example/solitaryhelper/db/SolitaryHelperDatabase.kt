package com.example.solitaryhelper.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.solitaryhelper.db.dao.Dao
import com.example.solitaryhelper.db.entitiy.UserProfile

@Database(entities = [UserProfile::class], exportSchema = false, version = 1)
abstract class SolitaryHelperDatabase() : RoomDatabase() {
    abstract val dataSource: Dao

    companion object {
        @Volatile
        private var INSTANCE: SolitaryHelperDatabase? = null
        fun getInstance(context: Context): SolitaryHelperDatabase = synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(
                context,
                SolitaryHelperDatabase::class.java,
                "SolitaryHelper_database"
            ).fallbackToDestructiveMigration()
                .build().also {
                    INSTANCE = it
                }
        }
    }
}