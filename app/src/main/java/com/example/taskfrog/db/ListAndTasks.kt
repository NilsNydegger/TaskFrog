package com.example.taskfrog.db

import androidx.room.Embedded
import androidx.room.Relation

data class ListAndTasks(
    @Embedded
    val froggyList: FroggyList,
    @Relation(
        parentColumn = "name",
        entityColumn = "list"
    )
    val tasks: List<Task>
)