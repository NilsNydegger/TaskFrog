package com.example.taskfrog.room

import androidx.room.*

interface FrogListDAO {

    //ListDAO

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNewList(frogList: FrogList)

    @Query("SELECT * FROM FrogList ORDER BY list_name ASC")
    fun getAllFrogLists(): ArrayList<FrogList>

    @Delete
    fun deleteFrogList(frogList: FrogList)

    @Update
    fun changeFrogListProperties(frogList: FrogList?, list_name: String?, list_description: String?)

}