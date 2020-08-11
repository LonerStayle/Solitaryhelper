package com.example.solitaryhelper.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import com.example.solitaryhelper.localdb.dao.Dao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job


class MainViewModel(private val dataSource: Dao) : ViewModel() {

    private val uiScope = CoroutineScope(Dispatchers.Main + Job())
    private val recordScope = CoroutineScope(Dispatchers.IO + Job())

    private val _viewPagerController = MutableLiveData<Boolean>()
    val viewPagerController: LiveData<Boolean>
        get() = _viewPagerController

    fun viewPagerControl(boolean: Boolean) {
        _viewPagerController.value = boolean
    }

}