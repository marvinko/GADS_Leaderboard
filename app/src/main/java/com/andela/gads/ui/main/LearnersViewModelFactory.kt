package com.andela.gads.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andela.gads.api.LearnersRepository

@Suppress("UNCHECKED_CAST")
class LearnersViewModelFactory(private val repository: LearnersRepository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LearnersViewModel(repository) as T
    }
}