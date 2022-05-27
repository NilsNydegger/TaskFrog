package com.example.taskfrog.room

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FrogTaskViewModel(application: Application, listId: Int): AndroidViewModel(application){

    private val frogTaskRepository: FrogTaskRepository
    var getAllTasks: LiveData<List<FrogTask>>
    init {
        val frogTaskDatabase = FrogDatabase.getDatabase(application)?.frogTaskDao()
        frogTaskRepository = FrogTaskRepository(frogTaskDatabase!!, listId)
        getAllTasks = frogTaskRepository.getAllFrogTasks(listId)
    }

    fun addFrogTask(frogTask: FrogTask){
        viewModelScope.launch(Dispatchers.IO) {
            frogTaskRepository.addFrogTask(frogTask)
        }
    }

    fun updateFrogTask(frogTask: FrogTask) {
        viewModelScope.launch(Dispatchers.IO) {
            frogTaskRepository.updateFrogTask(frogTask)
        }
    }

    fun deleteFrogTask(frogTask: FrogTask){
        viewModelScope.launch(Dispatchers.IO) {
            frogTaskRepository.deleteFrogTask(frogTask)
        }
    }

}
