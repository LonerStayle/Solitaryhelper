package com.example.solitaryhelper.viewmodel.factory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.solitaryhelper.database.localdb.dao.KaKaoDao
import com.example.solitaryhelper.repository.KaKaoTalkRepository
import com.example.solitaryhelper.viewmodel.KaKaoTalkViewModel


class KaKaoTalkViewModelFactory(dataSource: KaKaoDao):ViewModelProvider.Factory {
    private val repository = KaKaoTalkRepository(dataSource)
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

            @Suppress("unchecked_cast")
            return KaKaoTalkViewModel(repository) as T

    }
}