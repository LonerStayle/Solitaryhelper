package com.example.solitaryhelper.db.dao

import com.example.solitaryhelper.db.entitiy.UserProfile
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {
   @Query("SELECT * FROM UserProfile")
    fun getUserProfile():LiveData<List<UserProfile>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun userProfileInsert(userProfile: UserProfile)

    @Delete
    fun userProfileDelete(userProfile: UserProfile)

}