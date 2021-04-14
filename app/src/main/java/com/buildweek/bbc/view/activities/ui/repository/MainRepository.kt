package com.buildweek.bbc.view.activities.ui.repository

import androidx.lifecycle.LiveData
import com.buildweek.bbc.view.activities.ui.api.RetrofitBuilder
import com.buildweek.bbc.view.activities.ui.model.InShortsNews
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main

object MainRepository {

//    var job: CompletableJob? = null
//
//    fun getNews(category: String): LiveData<InShortsNews>{
//        job = Job()
//        return object: LiveData<InShortsNews>(){
//            override fun onActive() {
//                super.onActive()
//                job?.let {
//                    CoroutineScope(Dispatchers.IO + job!!).launch{
//                        val news = RetrofitBuilder.apiService.getNews(category)
//                        withContext(Main){
//                            value = news
//                            it.complete()
//                        }
//                    }
//                }
//            }
//        }
//    }
//    fun cancelJob(){
//        job?.cancel()
//    }
}