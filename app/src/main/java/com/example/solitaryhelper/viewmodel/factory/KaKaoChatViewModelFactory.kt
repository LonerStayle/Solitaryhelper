package com.example.solitaryhelper.viewmodel.factory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.solitaryhelper.localdb.dao.KaKaoTalkChatDataDao
import com.example.solitaryhelper.viewmodel.KaKaoChatViewModel


class KaKaoChatViewModelFactory():ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(KaKaoChatViewModel::class.java)) {
            @Suppress("unchecked_cast")
            return KaKaoChatViewModel() as T
        }
        throw IllegalAccessException("Unknown ViewModel")
    }
}