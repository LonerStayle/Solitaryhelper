package com.example.solitaryhelper.viewmodel.factory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.solitaryhelper.localdb.dao.RoomDao
import com.example.solitaryhelper.viewmodel.SkillViewModel
import com.example.solitaryhelper.viewmodel.SmsViewModel


class SmsViewModelFactory(private val dao:RoomDao):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SmsViewModel::class.java)) {
            @Suppress("unchecked_cast")
            return SmsViewModel(dao) as T
        }
        throw IllegalAccessException("Unknown ViewModel")
    }
}