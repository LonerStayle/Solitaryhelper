package com.example.solitaryhelper.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.solitaryhelper.database.localdb.dao.KaKaoChatDao
import com.example.solitaryhelper.repository.KaKaoChatDataRepository
import com.example.solitaryhelper.viewmodel.KaKaoChatViewModel


class KaKaoChatViewModelFactory( dataSource: KaKaoChatDao) : ViewModelProvider.Factory {
    private val repository = KaKaoChatDataRepository(dataSource)
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(KaKaoChatViewModel::class.java)) {
            @Suppress("unchecked_cast")
            return KaKaoChatViewModel(repository) as T
        }
        throw IllegalAccessException("Unknown ViewModel")
    }
}