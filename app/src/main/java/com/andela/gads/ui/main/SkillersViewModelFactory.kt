package com.andela.gads.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andela.gads.api.SkillersRepository

@Suppress("UNCHECKED_CAST")
class SkillersViewModelFactory(private val repository: SkillersRepository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SkillersViewModel(repository) as T
    }
}