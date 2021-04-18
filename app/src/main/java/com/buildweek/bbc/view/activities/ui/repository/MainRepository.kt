package com.buildweek.bbc.view.activities.ui.repository

import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.MutableLiveData
import com.buildweek.bbc.view.activities.ui.api.RetrofitBuilder
import com.buildweek.bbc.view.activities.ui.model.InShortsNews
import com.buildweek.bbc.view.activities.ui.model.LocalServerNews
import retrofit2.Call
import retrofit2.Response

object MainRepository {

    private var _newsList: MutableLiveData<LocalServerNews> = MutableLiveData()
    private var _newsLocalServerList: MutableLiveData<LocalServerNews> = MutableLiveData()

    fun getInshotsNewsData(): MutableLiveData<LocalServerNews> {
        return _newsList
    }

    fun inshortsApiCall(category: String) {
        val news = RetrofitBuilder.newsInstance.getNewsByRegion(category)
        news.enqueue(object : retrofit2.Callback<LocalServerNews> {
            override fun onFailure(call: Call<LocalServerNews>, t: Throwable) {
                Log.d("Sumeet", "Error in fetching news", t)
                _newsList.postValue(null)
            }

            override fun onResponse(
                call: Call<LocalServerNews>,
                response: Response<LocalServerNews>
            ) {
                val news = response.body()
                if (news != null) {
                    _newsList.postValue(response.body())
                }
            }
        })
    }

    fun callLocalApi(progressBar: ProgressBar) {
        val news = RetrofitBuilder.newsInstance.getWorldNews()
        news.enqueue(object : retrofit2.Callback<LocalServerNews> {
            override fun onFailure(call: Call<LocalServerNews>, t: Throwable) {
                _newsLocalServerList.postValue(null)
            }

            override fun onResponse(
                call: Call<LocalServerNews>,
                response: Response<LocalServerNews>
            ) {
                _newsLocalServerList.postValue(response.body())
                progressBar.visibility = View.GONE
            }

        })
    }

    fun getLocalServerNews(): MutableLiveData<LocalServerNews> {
        return _newsLocalServerList
    }

    fun newsByCategory(category: String) {
        val news = RetrofitBuilder.newsInstance.getNewsByCategory(category)
        news.enqueue(object : retrofit2.Callback<LocalServerNews> {
            override fun onFailure(call: Call<LocalServerNews>, t: Throwable) {
                Log.d("Sumeet", "Error in fetching news", t)
                _newsLocalServerList.postValue(null)
            }

            override fun onResponse(
                call: Call<LocalServerNews>,
                response: Response<LocalServerNews>
            ) {
                val news = response.body()
                if (news != null) {
                    _newsLocalServerList.postValue(response.body())
                }
            }
        })
    }

}