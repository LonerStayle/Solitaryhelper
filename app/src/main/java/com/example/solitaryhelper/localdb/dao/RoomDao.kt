package com.example.solitaryhelper.localdb.dao

import android.service.autofill.UserData
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.solitaryhelper.localdb.entitiy.UserProfile


@Dao
interface RoomDao {

    @Query("SELECT * FROM UserProfile")
    fun getUserProfile(): LiveData<UserProfile>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserProfile(userProfile: UserProfile)



}