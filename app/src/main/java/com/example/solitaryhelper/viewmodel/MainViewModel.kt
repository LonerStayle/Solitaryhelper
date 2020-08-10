package com.example.solitaryhelper.viewmodel

import androidx.lifecycle.ViewModel
import com.example.solitaryhelper.localdb.dao.Dao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class MainViewModel(private val dataSource: Dao) : ViewModel() {

    private val uiScope = CoroutineScope(Dispatchers.Main + Job())
    private val recordScope = CoroutineScope(Dispatchers.IO + Job())


}