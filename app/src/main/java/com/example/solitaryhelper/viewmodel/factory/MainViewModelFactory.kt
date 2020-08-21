package com.example.solitaryhelper.viewmodel.factory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.solitaryhelper.localdb.dao.RoomDao
import com.example.solitaryhelper.viewmodel.MainViewModel
import com.example.solitaryhelper.viewmodel.SkillViewModel


class MainViewModelFactory(private val dataSource:RoomDao):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("unchecked_cast")
            return MainViewModel(dataSource) as T
        }
        throw IllegalAccessException("Unknown ViewModel")
    }
}