package com.example.solitaryhelper.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import com.example.solitaryhelper.localdb.dao.SmsDao
import com.example.solitaryhelper.localdb.dao.UserDao
import com.example.solitaryhelper.localdb.entitiy.UserProfile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel(private val dataSource:UserDao):ViewModel() {

    private val iOScope = CoroutineScope(Dispatchers.IO+ Job())

    val userProfile:LiveData<UserProfile>
    get() = dataSource.getUserProfile()

    fun insertUserProfileId(id:String){
        iOScope.launch {
           dataSource.insertUserProfile(UserProfile(0,id,0,""))
        }
    }

}