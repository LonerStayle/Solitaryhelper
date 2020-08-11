package com.example.solitaryhelper.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.solitaryhelper.localdb.dao.Dao


class MainViewModelFactory():ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("unchecked_cast")
            return MainViewModel() as T
        }
        throw IllegalAccessException("Unknown ViewModel")
    }
}