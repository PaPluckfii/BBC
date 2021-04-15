package com.buildweek.bbc.view.activities.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.buildweek.bbc.view.activities.ui.model.InShortsNews
import com.buildweek.bbc.view.activities.ui.model.LocalServerNews
import com.buildweek.bbc.view.activities.ui.repository.MainRepository

class MainViewModel() : ViewModel(){

    val repository = MainRepository

    fun getInshotsData(): MutableLiveData<InShortsNews>{
        return repository.getInshotsNewsData()
    }

    fun callApi(category : String){
        repository.inshortsApiCall(category)
    }

    fun callLocalApi(){
        repository.callLocalApi()
    }

    fun getLocalServerNews(): MutableLiveData<LocalServerNews>{
        return repository.getLocalServerNews()
    }

}