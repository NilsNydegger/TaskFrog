package com.example.taskfrog.room

class FrogTaskRepository (private val frogTaskDAO: FrogTaskDAO, listId: Int) {

    val getAllFrogTasks: ArrayList<FrogTask> = frogTaskDAO.getAllTasksFromList(listId)

    suspend fun addFrogTask(frogTask: FrogTask){
        frogTaskDAO.addNewFrogTask(frogTask)
    }

}