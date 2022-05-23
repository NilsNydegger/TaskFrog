package com.example.taskfrog.room

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FrogListViewModel(application: Application): AndroidViewModel(application) {

    private val repository: FrogListRepository
    var getAllLists: List<FrogList>
    init {
        val frogListDatabase = FrogDatabase.getDatabase(application)?.frogListDao()
        repository = FrogListRepository(frogListDatabase!!)
        getAllLists = repository.getAllFrogLists()
    }

    fun addFrogList(frogList: FrogList){
        viewModelScope.launch(Dispatchers.IO){
            repository.addFrogList(frogList)
        }
    }

    fun getAllFrogLists(){
        viewModelScope.launch(Dispatchers.IO){
            repository.getAllFrogLists()
        }
    }

}
