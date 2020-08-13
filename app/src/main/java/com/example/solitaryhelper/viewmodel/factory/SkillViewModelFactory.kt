package com.example.solitaryhelper.viewmodel.factory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.solitaryhelper.viewmodel.SkillViewModel


class SkillViewModelFactory():ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SkillViewModel::class.java)) {
            @Suppress("unchecked_cast")
            return SkillViewModel() as T
        }
        throw IllegalAccessException("Unknown ViewModel")
    }
}