package com.example.taskfrog.room

import androidx.room.*
import com.google.gson.*
import java.util.*

class Converters {

    //ArrayLists
    @TypeConverter
    fun frogListArrayToJson(value: List<FrogList>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToFrogListArray(value: String) = Gson().fromJson(value, Array<FrogList>::class.java).toList()

    @TypeConverter
    fun frogTaskArrayToJson(value: List<FrogTask>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToFrogTaskArray(value: String) = Gson().fromJson(value, Array<FrogTask>::class.java).toList()

    //Dates
    @TypeConverter
    fun fromTimestampToDate(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun fromDateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }


}