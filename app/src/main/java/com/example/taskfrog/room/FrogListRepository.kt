package com.example.taskfrog.room

class FrogListRepository (private val frogListDAO: FrogListDAO) {

    val getAllFrogLists: ArrayList<FrogList> = frogListDAO.getAllFrogLists()

    suspend fun addFrogList(frogList: FrogList){
        frogListDAO.addNewList(frogList)
    }

}