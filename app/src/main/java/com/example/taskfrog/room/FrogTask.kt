package com.example.taskfrog.room

import androidx.room.*
import java.util.*

@Entity
data class FrogTask(
    @PrimaryKey(autoGenerate = true) val frogTaskId: Int?,
    @ColumnInfo var task_name: String?,
    @ColumnInfo var task_description: String?,
    @ColumnInfo var task_date: Date?,
    @ColumnInfo var task_done: Boolean?,
    @ColumnInfo var belongs_to_list: Int?
)
