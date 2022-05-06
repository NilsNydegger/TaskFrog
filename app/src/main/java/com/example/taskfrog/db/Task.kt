package com.example.taskfrog.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity (tableName = "task")
class Task (
    @field:ColumnInfo(name = "name") val name: String,
    @field:ColumnInfo(name = "description") val description: String,
    private val foreignKey: ForeignKey
){
    @PrimaryKey(autoGenerate = true)
    val id = 0
    @ForeignKey()
}