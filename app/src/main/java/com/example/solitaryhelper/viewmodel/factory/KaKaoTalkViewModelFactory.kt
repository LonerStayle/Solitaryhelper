package com.example.solitaryhelper.viewmodel.factory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.solitaryhelper.localdb.dao.KaKaoDao
import com.example.solitaryhelper.viewmodel.KaKaoChatViewModel
import com.example.solitaryhelper.viewmodel.KaKaoTalkViewModel
import javax.sql.DataSource


class KaKaoTalkViewModelFactory(private val dataSource: KaKaoDao):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

            @Suppress("unchecked_cast")
            return KaKaoTalkViewModel(dataSource) as T

    }
}