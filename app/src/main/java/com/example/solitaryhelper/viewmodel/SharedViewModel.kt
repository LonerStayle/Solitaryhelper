package com.example.solitaryhelper.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import androidx.lifecycle.ViewModel


class SharedViewModel : ViewModel() {
    private val _myChatSave = MutableLiveData<String>()
    val myChatSave: LiveData<String>
        get() = _myChatSave

    fun upDateMyChat(myChat:String){
        _myChatSave.postValue(myChat)
    }

}

