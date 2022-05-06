package com.example.taskfrog.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity (
    foreignKeys = [ForeignKey(
        entity = FroggyList::class,
        parentColumns = arrayOf("name"),
        childColumns = arrayOf("list"),
        onDelete = ForeignKey.CASCADE
    )]
        )
data class Task (
    @PrimaryKey
    val name:String,
    @ColumnInfo(index = true)
    val description: String,
    val list: String
)