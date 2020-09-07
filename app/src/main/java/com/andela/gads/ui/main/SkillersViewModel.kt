package com.andela.gads.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andela.gads.Coroutines
import com.andela.gads.api.Skiller
import com.andela.gads.api.SkillersRepository
import kotlinx.coroutines.Job

class SkillersViewModel(private val repository: SkillersRepository) : ViewModel() {

    private lateinit var job: Job

    private val _skillers = MutableLiveData<List<Skiller>>()
    val skillers: LiveData<List<Skiller>> get() = _skillers

    fun getSkillers(){
        job = Coroutines.ioThenMAin(
                { repository.getSkillers() },
                { _skillers.value = it }
        )

    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }

}
