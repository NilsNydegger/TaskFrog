package com.example.taskfrog.room

import android.content.*
import androidx.room.*

@Database(entities = [FrogList::class, FrogTask::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class FrogDatabase : RoomDatabase() {
    abstract fun frogListDao() : FrogListDAO
    abstract fun frogTaskDao() : FrogTaskDAO


    //TODO: I have honestly no clue if this works... but I gotta get going now

    companion object {
        //We're making a Singleton FrogDatabase
        //Making it Volatile means that writes will be instantly visible to other threads
        @Volatile
        private var INSTANCE: FrogDatabase? = null

        fun getInstance(context: Context): FrogDatabase? {
            if (INSTANCE == null) {
                synchronized(FrogDatabase::class) {
                    val tempInstance = Room.databaseBuilder(
                        context.applicationContext,
                        FrogDatabase::class.java,
                        "frog.db"
                    ).build()
                    INSTANCE = tempInstance
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}