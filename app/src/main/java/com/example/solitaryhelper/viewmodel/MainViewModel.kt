package com.example.solitaryhelper.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.solitaryhelper.db.dao.Dao
import com.example.solitaryhelper.db.entitiy.UserProfile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.sql.CommonDataSource

class MainViewModel(private val dataSource: Dao) : ViewModel() {

    private val uiScope = CoroutineScope(Dispatchers.Main + Job())
    private val recordScope = CoroutineScope(Dispatchers.IO + Job())
    val userProfile: LiveData<UserProfile>
        get() = dataSource.getUserProfile()


    fun idCreate(id: String) {
        recordScope.launch {
            dataSource.userProfileUpdate(UserProfile(id,null))
        }
    }
}