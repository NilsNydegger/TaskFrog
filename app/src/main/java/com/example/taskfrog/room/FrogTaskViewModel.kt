package com.example.taskfrog.room

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FrogTaskViewModel(application: Application, listId: Int): AndroidViewModel(application){

    private val frogTaskRepository: FrogTaskRepository
    private var getAllTasks: LiveData<List<FrogTask>>
    init {
        val frogTaskDatabase = FrogDatabase.getDatabase(application)?.frogTaskDao()
        frogTaskRepository = FrogTaskRepository(frogTaskDatabase!!, listId)
        getAllTasks = frogTaskRepository.getAllFrogTasks(listId)
    }

    fun addFrogList(frogTask: FrogTask){
        viewModelScope.launch(Dispatchers.IO) {
            frogTaskRepository.addFrogTask(frogTask)
        }
    }

}