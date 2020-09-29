package com.example.solitaryhelper.repository

import androidx.lifecycle.LiveData
import com.example.solitaryhelper.database.localdb.dao.UserDao
import com.example.solitaryhelper.database.localdb.entitiy.UserProfile

interface UserDataSource{

    fun getUserProfile(): LiveData<UserProfile>

    fun insertUserProfile(userProfile: UserProfile)
}

class UserRepository(private val dataSource: UserDao) :UserDataSource {
    override fun getUserProfile() = dataSource.getUserProfile()

    override fun insertUserProfile(userProfile: UserProfile) {
        dataSource.insertUserProfile(userProfile)
    }
}
