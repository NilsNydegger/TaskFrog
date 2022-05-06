package com.example.taskfrog.db

import android.app.Application
import androidx.lifecycle.LiveData

class FroggyListRepository internal constructor(application: Application?) {
    private val mFroggyDAO: FroggyDAO

    val allFroggyLists: LiveData<List<FroggyList?>?>?

    fun insert(froggyList: FroggyList) {
        FroggyRoomDatabase.froggyWriteExecutor.execute { mFroggyDAO.save(froggyList)}
    }

    init {
        val db = FroggyRoomDatabase.getDatabase(application!!)
        mFroggyDAO = db!!.froggyListDao()
        allFroggyLists = mFroggyDAO.froggyList
    }
}