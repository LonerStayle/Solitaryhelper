package com.example.solitaryhelper.viewmodel.factory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.solitaryhelper.viewmodel.SharedViewModel
import com.example.solitaryhelper.viewmodel.SkillViewModel


class SharedViewModelFactory():ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SharedViewModel::class.java)) {
            @Suppress("unchecked_cast")
            return SharedViewModel() as T
        }
        throw IllegalAccessException("Unknown ViewModel")
    }
}