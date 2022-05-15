package com.example.taskfrog.room

import androidx.room.*

data class FrogListWithFrogTasks(
    @Embedded val frog_list: FrogList,
    @Relation(
        parentColumn = "frogListId",
        entityColumn = "frogTaskId"
    )
    val frog_tasks: List<FrogTask>
)
