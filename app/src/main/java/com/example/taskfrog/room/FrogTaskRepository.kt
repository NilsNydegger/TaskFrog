package com.example.taskfrog.room

import androidx.lifecycle.LiveData

class FrogTaskRepository (private val frogTaskDAO: FrogTaskDAO, listId: Int) {

    fun getAllFrogTasks(listId: Int): LiveData<List<FrogTask>> = frogTaskDAO.getAllTasksFromList(listId)

    suspend fun addFrogTask(frogTask: FrogTask){
        frogTaskDAO.addNewFrogTask(frogTask)
    }

    suspend fun updateFrogTask(frogTask: FrogTask){
        frogTaskDAO.changeFrogTaskProperties(frogTask)
    }

    suspend fun deleteFrogTask(frogTask: FrogTask){
        frogTaskDAO.deleteFrogTask(frogTask)
    }

}
