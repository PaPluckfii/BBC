package com.buildweek.bbc.view.activities.ui.viewmodel

import android.widget.ProgressBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.buildweek.bbc.view.activities.ui.model.InShortsNews
import com.buildweek.bbc.view.activities.ui.model.LocalServerNews
import com.buildweek.bbc.view.activities.ui.repository.MainRepository

class MainViewModel() : ViewModel() {

    val repository = MainRepository

    fun getInshotsData(): MutableLiveData<LocalServerNews> {
        return repository.getInshotsNewsData()
    }

    fun newsByRegion(name: String) {
        repository.inshortsApiCall(name)
    }

    fun newsByCategory(category: String) {
        repository.newsByCategory(category)
    }

    fun worldNews(progressBar: ProgressBar) {
        repository.callLocalApi(progressBar)
    }

    fun getLocalServerNews(): MutableLiveData<LocalServerNews> {
        return repository.getLocalServerNews()
    }

}