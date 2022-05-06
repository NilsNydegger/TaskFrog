package com.example.taskfrog.db

import androidx.room.*

@Dao
interface FroggyDAO {
    @Insert
    suspend fun save(froggyList: FroggyList)

    @Insert
    suspend fun save(vararg froggyList: FroggyList)

    @Transaction
    @Query("SELECT * FROM list")
    suspend fun getAll():List<ListAndTasks>

    @Transaction
    @Query("SELECT * FROM list WHERE name = :id")
    suspend fun getByFroggyListId(id: String): ListAndTasks

    //successfully suspended fun therefore likely resignation
}