package com.example.solitaryhelper.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.solitaryhelper.db.entitiy.UserProfile
import androidx.lifecycle.LiveData

@Dao
interface Dao {
   @Query("SELECT * FROM UserProfile")
    fun getUserProfile():LiveData<UserProfile>

    @Insert
    fun userProfileInsert(userProfile: UserProfile)
    @Update
    fun userProfileUpdate(userProfile: UserProfile)

}