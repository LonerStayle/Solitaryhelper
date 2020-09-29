package com.example.solitaryhelper.viewmodel.factory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.solitaryhelper.repository.NaverApiRepository
import com.example.solitaryhelper.viewmodel.SkillViewModel
import com.example.solitaryhelper.viewmodel.TopicViewModel


class TopicViewModelFactory:ViewModelProvider.Factory {
    private val repository =  NaverApiRepository()

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TopicViewModel::class.java)) {
            @Suppress("unchecked_cast")
            return TopicViewModel(repository) as T
        }
        throw IllegalAccessException("Unknown ViewModel")
    }
}