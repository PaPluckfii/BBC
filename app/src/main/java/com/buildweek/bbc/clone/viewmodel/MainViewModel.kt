package com.buildweek.bbc.clone.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.buildweek.bbc.clone.data.remote.model.LocalServerNews
import com.buildweek.bbc.clone.repository.MainRepository

class MainViewModel() : ViewModel() {

    val repository = MainRepository

    fun getLocalServerNews(): MutableLiveData<LocalServerNews> {
        return repository.getLocalServerNews()
    }

    fun newsByRegion(name: String) {
        repository.newsByRegion(name)
    }

    fun newsByCategory(category: String) {
        repository.newsByCategory(category)
    }

    fun worldNews() {
        repository.worldNews()
    }

    fun getNewsByTag(tag: String){
        return repository.newsByTag(tag)
    }

}