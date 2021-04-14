package com.buildweek.bbc.view.activities.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.buildweek.bbc.view.activities.ui.model.InShortsNews
import com.buildweek.bbc.view.activities.ui.repository.MainRepository

class MainViewModel : ViewModel(){

//    private val _news : MutableLiveData<String> = MutableLiveData()
//
//    val news : LiveData<InShortsNews> = Transformations
//            .switchMap(_news){
//                MainRepository.getUser(it)
//            }
//    fun setCategory(category: String){
//        val update = category
//        if (_news.value == update){
//            return
//        }
//        _news.value = update
//    }
//    fun cancelJobs(){
//        MainRepository.cancelJob()
//    }
}