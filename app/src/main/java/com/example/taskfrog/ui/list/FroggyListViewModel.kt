package com.example.taskfrog.ui.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.taskfrog.db.FroggyList
import com.example.taskfrog.db.FroggyListRepository

class FroggyListViewModel(application: Application?) : AndroidViewModel(application!!){
    private val mRepository: FroggyListRepository
    val allFroggyList: LiveData<List<FroggyList?>?>?
    fun insert(froggyList: FroggyList) {
        mRepository.insert(froggyList)
    }

    init {
        mRepository = FroggyListRepository(application)
        allFroggyList = mRepository.allFroggyLists
    }
}