package com.example.taskfrog.room

import androidx.lifecycle.*
import androidx.room.*

@Dao
interface FrogListDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNewList(frogList: FrogList?)

    @Query("SELECT * FROM FrogList ORDER BY list_name ASC")
    fun getAllFrogLists(): LiveData<List<FrogList>>

    @Delete
    fun deleteFrogList(frogList: FrogList)

    @Update
    fun changeFrogListProperties(frogList: FrogList)

}
