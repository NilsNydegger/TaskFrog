package com.example.taskfrog.room

import androidx.room.*

@Entity
data class FrogList(
    @PrimaryKey(autoGenerate = true) var frogListId: Int? = null,
    @ColumnInfo var list_name: String?,
    )
