package com.buildweek.bbc.view.activities.ui.repository

import android.util.Log
import android.widget.ProgressBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.buildweek.bbc.view.activities.ui.api.RetrofitBuilder
import com.buildweek.bbc.view.activities.ui.model.InShortsNews
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import retrofit2.Call
import retrofit2.Response

object MainRepository {

    private var _newsList : MutableLiveData<InShortsNews> = MutableLiveData()

    fun getInshotsNewsData(): MutableLiveData<InShortsNews> {
        return _newsList
    }

    fun inshortsApiCall(category: String){
        val news = RetrofitBuilder.newsInstance.getNews(category)
        news.enqueue(object : retrofit2.Callback<InShortsNews> {
            override fun onFailure(call: Call<InShortsNews>, t: Throwable) {
                Log.d("Sumeet","Error in fetching news",t)
                _newsList.postValue(null)
            }

            override fun onResponse(call: Call<InShortsNews>, response: Response<InShortsNews>) {
                val news = response.body()
                if(news!=null){
                    _newsList.postValue(response.body())
                }
            }
        })
    }
}