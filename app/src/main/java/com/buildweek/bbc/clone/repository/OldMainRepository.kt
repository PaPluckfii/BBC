package com.buildweek.bbc.clone.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.buildweek.bbc.clone.data.remote.api.RetrofitBuilder
import com.buildweek.bbc.clone.data.remote.model.springboot.LocalServerNews
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

object OldMainRepository {

    private var _newsLocalServerList: MutableLiveData<LocalServerNews> = MutableLiveData()

    fun getLocalServerNews(): MutableLiveData<LocalServerNews> {
        return _newsLocalServerList
    }

    fun newsByRegion(region: String) {
        val news = RetrofitBuilder.NEWS_INSTANCE.getNewsByRegion(region)
        CoroutineScope(Dispatchers.IO).launch {
            news.enqueue(object : retrofit2.Callback<LocalServerNews> {
                override fun onFailure(call: Call<LocalServerNews>, t: Throwable) {
                    Log.d("Sumeet", "Error in fetching news", t)
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

    fun worldNews() {
        val news = RetrofitBuilder.NEWS_INSTANCE.getWorldNews()
        CoroutineScope(Dispatchers.IO).launch {
            news.enqueue(object : retrofit2.Callback<LocalServerNews> {
                override fun onFailure(call: Call<LocalServerNews>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<LocalServerNews>,
                    response: Response<LocalServerNews>
                ) {
                    _newsLocalServerList.postValue(response.body())
                }

            })
        }
    }

    fun newsByTag(tag: String) {
        val news = RetrofitBuilder.NEWS_INSTANCE.newsByTag(tag)
        CoroutineScope(Dispatchers.IO).launch {
            news.enqueue(object : retrofit2.Callback<LocalServerNews> {
                override fun onFailure(call: Call<LocalServerNews>, t: Throwable) {
                    Log.d("Sumeet", "Error in fetching news", t)
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


    fun newsByCategory(category: String) {
        val news = RetrofitBuilder.NEWS_INSTANCE.getNewsByCategory(category)
        CoroutineScope(Dispatchers.IO).launch {
            news.enqueue(object : retrofit2.Callback<LocalServerNews> {
                override fun onFailure(call: Call<LocalServerNews>, t: Throwable) {
                    Log.d("Sumeet", "Error in fetching news", t)

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

}