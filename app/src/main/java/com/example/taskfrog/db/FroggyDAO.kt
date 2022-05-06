package com.example.taskfrog.db

import androidx.room.*

@Dao
interface FroggyDAO {
    @Insert
    fun save(froggyList: FroggyList)

    @Insert
    fun save(vararg froggyList: FroggyList)

    @Transaction
    @Query("SELECT * FROM list")
    fun getAll():List<ListAndTasks>

    @Transaction
    @Query("SELECT * FROM list WHERE name = :id")
    fun getByFroggyListId(id: String): ListAndTasks

    //successfully suspended fun therefore likely resignation
}