package com.example.taskfrog.room

import androidx.room.*

interface FrogListDAO {

    //ListDAO

    @Insert
    fun addNewList(list_name: String?, list_description: String?)

    @Query("SELECT * FROM FrogList")
    fun getAllFrogLists(): ArrayList<FrogList>

    @Delete
    fun deleteFrogList(frogList: FrogList)

    @Update
    fun changeFrogListProperties(frogList: FrogList?, list_name: String?, list_description: String?)

}