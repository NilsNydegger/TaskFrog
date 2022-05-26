package com.example.taskfrog.room

import androidx.lifecycle.LiveData

class FrogListRepository (private val frogListDAO: FrogListDAO) {

    fun getAllFrogLists(): LiveData<List<FrogList>> = frogListDAO.getAllFrogLists()

    suspend fun addFrogList(frogList: FrogList?){
        frogListDAO.addNewList(frogList)
    }

    suspend fun updateFrogList(frogList: FrogList){
        frogListDAO.changeFrogListProperties(frogList)
    }

    suspend fun deleteFrogList(frogList: FrogList){
        frogListDAO.deleteFrogList(frogList)
    }

}
