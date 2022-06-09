package com.example.taskfrog.room

import androidx.lifecycle.LiveData
import androidx.room.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

@Dao
interface FrogTaskDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNewFrogTask(frogTask: FrogTask?)

    @Query("SELECT * FROM FrogTask WHERE belongs_to_list = :frogListId ORDER BY task_name ASC")
    fun getAllTasksFromList(frogListId: Int): LiveData<List<FrogTask>>

    @Query("SELECT * FROM FrogTask WHERE task_date = :taskDate ORDER BY task_name ASC")
    fun getAllTasksFromDate(taskDate: String): List<FrogTask>

    @Delete
    fun deleteFrogTask(frogTask: FrogTask?)

    @Update
    fun changeFrogTaskProperties(frogTask: FrogTask?)

}
