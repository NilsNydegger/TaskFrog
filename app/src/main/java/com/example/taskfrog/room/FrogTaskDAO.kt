package com.example.taskfrog.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FrogTaskDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNewFrogTask(frogTask: FrogTask?)

    //TODO: Test this! Mightn't be correct but it seems nice
    @Query("SELECT * FROM FrogTask WHERE belongs_to_list = :frogListId ORDER BY task_name ASC")
    fun getAllTasksFromList(frogListId: Int): LiveData<List<FrogTask>>

    @Delete
    fun deleteFrogTask(frogTask: FrogTask?)

    @Update
    fun changeFrogTaskProperties(frogTask: FrogTask?)

}