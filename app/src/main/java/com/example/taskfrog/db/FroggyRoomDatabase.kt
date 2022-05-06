package com.example.taskfrog.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.concurrent.Executors

@Database(entities = [FroggyList::class, Task::class], version = 1, exportSchema = false)
abstract class FroggyRoomDatabase : RoomDatabase() {
    abstract fun froggyListDao(): FroggyDAO

    companion object {
        @Volatile
        private var INSTANCE: FroggyRoomDatabase? = null
        private const val NUMBER_OF_THREADS = 4
        val froggyWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS)
        fun getDatabase(context: Context): FroggyRoomDatabase? {

            if (INSTANCE == null) {

                synchronized(FroggyRoomDatabase::class.java) {
                    if (INSTANCE == null) {

                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            FroggyRoomDatabase::class.java, "list"
                        ).addCallback(
                            sRoomDatabaseCallback
                        ).build()
                    }
                }
            }

            return INSTANCE
        }

        private val sRoomDatabaseCallback: Callback = object : Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)

            }
        }
    }
}