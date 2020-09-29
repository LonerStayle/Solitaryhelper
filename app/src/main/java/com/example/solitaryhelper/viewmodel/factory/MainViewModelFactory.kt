package com.example.solitaryhelper.viewmodel.factory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.solitaryhelper.database.localdb.dao.UserDao
import com.example.solitaryhelper.viewmodel.MainViewModel


class MainViewModelFactory(private val dataSource:UserDao):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("unchecked_cast")
            return MainViewModel(dataSource) as T
        }
        throw IllegalAccessException("Unknown ViewModel")
    }
}