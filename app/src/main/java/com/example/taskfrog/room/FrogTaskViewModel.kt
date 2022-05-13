package com.example.taskfrog.room

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*

class FrogTaskViewModel(application: Application): AndroidViewModel(application){

    private val getAllTasks: ArrayList<FrogTask>
    private val frogTaskRepository: FrogTaskRepository

    init {
        val frogTaskDAO = FrogDatabase.getInstance(application)?.frogTaskDao()
        frogTaskRepository = frogTaskDAO?.let { FrogTaskRepository(it) }!!
        // ^ The parameter ListID is missing, I don't know how that works, I'll look at it after a break
        getAllTasks = frogTaskRepository.getAllFrogTasks
    }

    fun addFrogList(frogTask: FrogTask){
        viewModelScope.launch(Dispatchers.IO) {
            frogTaskRepository.addFrogTask(frogTask)
        }
    }

}

*/