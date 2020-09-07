package com.andela.gads.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andela.gads.Coroutines
import com.andela.gads.api.Learner
import com.andela.gads.api.LearnersRepository
import kotlinx.coroutines.Job

class LearnersViewModel(private val repository: LearnersRepository) : ViewModel() {

    private lateinit var job: Job

    private val _learners = MutableLiveData<List<Learner>>()
    val learners: LiveData<List<Learner>> get() = _learners

    fun getLearners(){
        job = Coroutines.ioThenMAin(
                { repository.getLearners() },
                { _learners.value = it }
        )

    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }

}
