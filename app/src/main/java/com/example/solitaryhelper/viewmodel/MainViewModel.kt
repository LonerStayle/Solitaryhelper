package com.example.solitaryhelper.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import com.example.solitaryhelper.database.localdb.dao.UserDao
import com.example.solitaryhelper.database.localdb.entitiy.UserProfile
import com.example.solitaryhelper.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel(private val repository: UserRepository):ViewModel() {

    private val iOScope = CoroutineScope(Dispatchers.IO+ Job())

    val userProfile:LiveData<UserProfile>
    get() = repository.getUserProfile()

    fun insertUserProfileId(id:String){
        iOScope.launch {
           repository.insertUserProfile(UserProfile(0,id,0,""))
        }
    }

}