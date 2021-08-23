package com.buildweek.bbc.clone.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buildweek.bbc.BuildConfig
import com.buildweek.bbc.clone.data.remote.model.opensourceapi.OpenSourceResponse
import com.buildweek.bbc.clone.data.remote.model.springboot.LocalServerNews
import com.buildweek.bbc.clone.repository.MainRepository
import com.buildweek.bbc.clone.repository.OldMainRepository
import com.buildweek.bbc.clone.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
   private val repository: MainRepository
) : ViewModel() {

    private val _newsList : MutableLiveData<Resource<OpenSourceResponse>> = MutableLiveData()
    private var newsListResponse : OpenSourceResponse? = null
    var currentPage : Int = 1
    val newsList : LiveData<Resource<OpenSourceResponse>> = _newsList

    /**
     * Function to get results from api
     */
    fun getAllNews(
        country : String,
        category : String,
        source : String,
        keyword : String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            _newsList.postValue(Resource.Loading())
            val response = repository.getNewsFromOpenApi(
                country,
                category,
                source,
                keyword,
                BuildConfig.OPEN_SOURCE_API_KEY,
                currentPage++
            )
            _newsList.postValue(handleApiResponse(response))
        }
    }

    /**
     * Function to wrap the api response in Resource class
     */
    private fun handleApiResponse(response : Response<OpenSourceResponse>) : Resource<OpenSourceResponse>{
        if(response.isSuccessful && response.body() != null){
            if(newsListResponse == null){
                newsListResponse = response.body()
            }else{
                val oldList = newsListResponse?.articles
                val newList = response.body()?.articles
                oldList?.addAll(newList!!)
            }
            return Resource.Success(newsListResponse ?: response.body())
        }
        return Resource.Error(response.message())
    }

    //TODO Remove Unnecessary Code
    val oldRepository = OldMainRepository

    fun getLocalServerNews(): MutableLiveData<LocalServerNews> {
        return oldRepository.getLocalServerNews()
    }

    fun newsByRegion(name: String) {
        oldRepository.newsByRegion(name)
    }

    fun newsByCategory(category: String) {
        oldRepository.newsByCategory(category)
    }

    fun worldNews() {
        oldRepository.worldNews()
    }

    fun getNewsByTag(tag: String){
        return oldRepository.newsByTag(tag)
    }

}