package com.example.solitaryhelper.localdb


//@Database(entities = [], exportSchema = false, version = 1)
//abstract class SolitaryHelperDatabase : RoomDatabase() {
//    abstract val dataSource: KaKaoTalkChatDataDao
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
