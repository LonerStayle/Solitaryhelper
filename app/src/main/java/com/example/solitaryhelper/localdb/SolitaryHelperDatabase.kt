package com.example.solitaryhelper.localdb

import android.content.Context
import androidx.room.*
import com.example.solitaryhelper.localdb.dao.RoomDao


//@Database(entities = , exportSchema = false, version = 1)
//abstract class SolitaryHelperDatabase : RoomDatabase() {
//    abstract val dataSource: RoomDao
//
//    companion object {
//        @Volatile
//        private var INSTANCE: SolitaryHelperDatabase? = null
//        fun getInstance(context: Context): SolitaryHelperDatabase = synchronized(this) {
//            INSTANCE ?: Room.databaseBuilder(
//                context,
//                SolitaryHelperDatabase::class.java,
//                "SolitaryHelper_database"
//            ).fallbackToDestructiveMigration()
//                .build().also {
//                    INSTANCE = it
//                }
//        }
//    }
//}
