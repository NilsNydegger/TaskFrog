package com.example.taskfrog.room

import androidx.room.*

interface FrogTaskDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNewFrogTask(frogTask: FrogTask?)

    //TODO: Test this! Mightn't be correct but it seems nice
    @Query("SELECT * FROM FrogTask WHERE belongs_to_list = :frogListId ORDER BY task_name ASC")
    fun getAllTasksFromList(frogListId: Int?): ArrayList<FrogTask>

    @Delete
    fun deleteFrogTask(frogTask: FrogTask?)

    @Update
    fun changeFrogTaskProperties(frogTask: FrogTask?, task_name: String?, task_description: String?, task_date: String?)

}