package com.example.taskfrog.room

import androidx.room.*

interface FrogTaskDAO {

    @Insert
    fun addNewFrogTask(task_name: String?, task_description: String?, task_date: String?, frogListId: Int?)

    //TODO: Test this! Mightn't be correct *gradle gradle* but it seems nice
    @Query("SELECT * FROM FrogTask WHERE belongs_to_list = :frogListId")
    fun selectAllTasksFromList(frogListId: Int?): ArrayList<FrogTask>

    @Delete
    fun deleteFrogTask(frogTask: FrogTask?)

    @Update
    fun changeFrogTaskProperties(frogTask: FrogTask?, task_name: String?, task_description: String?, task_date: String?)

}