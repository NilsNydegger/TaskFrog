package com.example.taskfrog.room

import android.app.Application
import androidx.lifecycle.*
import com.example.taskfrog.ui.list.TaskFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class FrogTaskViewModel(application: Application): AndroidViewModel(application){

    private val frogTaskRepository: FrogTaskRepository
    private var listId: Int = 0
    private var frogDate: String = "12.12.2000"
    var getAllTasks: LiveData<List<FrogTask>>? = null
    var getAllTasksFromDate: List<FrogTask> = emptyList()
    //This gets instantly initialized, just by our own initialisation - bad practice but temp fix
    init {
        val frogTaskDatabase = FrogDatabase.getDatabase(application)?.frogTaskDao()
        frogTaskRepository = FrogTaskRepository(frogTaskDatabase!!)
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

    fun lateInitialize(tempListId: Int){
        listId = tempListId
        getAllTasks = frogTaskRepository.getAllFrogTasks(listId)
    }

    fun dateInitialize(date: String){
        frogDate = date
        getAllTasksFromDate = frogTaskRepository.getFrogTasksFromDate(frogDate)
    }

    private fun updateDateInView(frogDate: LocalDate): String {
        val date: String
        val myFormat = "dd.MM.yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.GERMAN)
        date = sdf.format(frogDate)
        return date
    }
}
