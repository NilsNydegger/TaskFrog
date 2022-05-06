package com.example.taskfrog.db

import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "list")
data class FroggyList(
    @PrimaryKey
    val name: String,
    @ColumnInfo(index = true)
    val description: String
)