package com.example.taskfrog.room

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FrogTaskViewModel(application: Application) : AndroidViewModel(application) {

    private val frogTaskRepository: FrogTaskRepository
    private var listId: Int = 0
    private var tempFrogDate: String = "12.12.2000" //TempString in case anything goes wrong
    var getAllTasks: LiveData<List<FrogTask>>? = null
    var getAllTasksFromDate: List<FrogTask> = emptyList()

    init {
        val frogTaskDatabase = FrogDatabase.getDatabase(application)?.frogTaskDao()
        frogTaskRepository = FrogTaskRepository(frogTaskDatabase!!)
    }

    fun addFrogTask(frogTask: FrogTask) {
        viewModelScope.launch(Dispatchers.IO) {
            frogTaskRepository.addFrogTask(frogTask)
        }
    }

    fun updateFrogTask(frogTask: FrogTask) {
        viewModelScope.launch(Dispatchers.IO) {
            frogTaskRepository.updateFrogTask(frogTask)
        }
    }

    fun deleteFrogTask(frogTask: FrogTask) {
        viewModelScope.launch(Dispatchers.IO) {
            frogTaskRepository.deleteFrogTask(frogTask)
        }
    }

    //Technically not an initialization, technically not best practice, technically works though
    fun lateInitialize(tempListId: Int) {
        listId = tempListId
        getAllTasks = frogTaskRepository.getAllFrogTasks(listId)
    }

    //Same as lateInitialize, just with a different param
    fun dateInitialize(date: String) {
        tempFrogDate = date
        viewModelScope.launch(Dispatchers.IO) {
            getAllTasksFromDate = frogTaskRepository.getFrogTasksFromDate(tempFrogDate)
        }
    }
}
