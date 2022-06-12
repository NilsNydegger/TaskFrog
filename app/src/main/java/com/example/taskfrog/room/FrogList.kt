package com.example.taskfrog.room

import androidx.room.*

@Entity
data class FrogList(
    @PrimaryKey(autoGenerate = true) var frogListId: Int?,
    @ColumnInfo var list_name: String?,
)
