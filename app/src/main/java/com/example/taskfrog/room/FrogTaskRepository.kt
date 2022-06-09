package com.example.taskfrog.room

import androidx.lifecycle.LiveData
import java.time.LocalDate
import java.util.*

class FrogTaskRepository(private val frogTaskDAO: FrogTaskDAO) {

    fun getAllFrogTasks(listId: Int): LiveData<List<FrogTask>> =
        frogTaskDAO.getAllTasksFromList(listId)

    fun getFrogTasksFromDate(frogDate: String): List<FrogTask> =
        frogTaskDAO.getAllTasksFromDate(frogDate)

    suspend fun addFrogTask(frogTask: FrogTask) {
        frogTaskDAO.addNewFrogTask(frogTask)
    }

    fun updateFrogTask(frogTask: FrogTask) {
        frogTaskDAO.changeFrogTaskProperties(frogTask)
    }

    fun deleteFrogTask(frogTask: FrogTask) {
        frogTaskDAO.deleteFrogTask(frogTask)
    }

}
