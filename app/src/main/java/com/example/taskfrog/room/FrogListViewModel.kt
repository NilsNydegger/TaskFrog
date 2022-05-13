package com.example.taskfrog.room

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FrogListViewModel(application: Application): AndroidViewModel(application) {

    private val getAllLists: ArrayList<FrogList>
    private val frogListRepository: FrogListRepository

    init {
        val frogListDAO = FrogDatabase.getInstance(application)?.frogListDao()
        frogListRepository = frogListDAO?.let { FrogListRepository(it) }!! //What the fuck...
        getAllLists = frogListRepository.getAllFrogLists
    }

    fun addFrogList(frogList: FrogList){
        viewModelScope.launch(Dispatchers.IO) {
            frogListRepository.addFrogList(frogList)
        }
    }

}