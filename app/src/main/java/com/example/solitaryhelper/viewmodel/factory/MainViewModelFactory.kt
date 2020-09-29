package com.example.solitaryhelper.viewmodel.factory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.solitaryhelper.database.localdb.dao.UserDao
import com.example.solitaryhelper.repository.UserRepository
import com.example.solitaryhelper.viewmodel.MainViewModel


class MainViewModelFactory( dataSource:UserDao):ViewModelProvider.Factory {
    private val repository = UserRepository(dataSource)
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("unchecked_cast")
            return MainViewModel(repository) as T
        }
        throw IllegalAccessException("Unknown ViewModel")
    }
}