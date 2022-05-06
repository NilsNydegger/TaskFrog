package com.example.taskfrog.db

import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "list")
class List(
    @field:ColumnInfo(name = "name") val name: String
)