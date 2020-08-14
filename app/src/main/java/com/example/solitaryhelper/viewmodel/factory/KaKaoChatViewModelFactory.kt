package com.example.solitaryhelper.viewmodel.factory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.solitaryhelper.localdb.dao.RoomDao
import com.example.solitaryhelper.viewmodel.KaKaoChatViewModel
import com.example.solitaryhelper.viewmodel.SharedViewModel
import com.example.solitaryhelper.viewmodel.SkillViewModel


class KaKaoChatViewModelFactory(private val dao:RoomDao):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(KaKaoChatViewModel::class.java)) {
            @Suppress("unchecked_cast")
            return KaKaoChatViewModel(dao) as T
        }
        throw IllegalAccessException("Unknown ViewModel")
    }
}