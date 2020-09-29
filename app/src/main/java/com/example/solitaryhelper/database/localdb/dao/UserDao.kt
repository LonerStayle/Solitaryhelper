package com.example.solitaryhelper.database.localdb.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.solitaryhelper.database.localdb.entitiy.UserProfile

@Dao
interface UserDao {
    @Query("SELECT * FROM UserProfile")
    fun getUserProfile(): LiveData<UserProfile>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserProfile(userProfile: UserProfile)

}