package com.buildweek.bbc.view.activities.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.buildweek.bbc.R
import com.buildweek.bbc.view.activities.ui.api.RetrofitBuilder
import com.buildweek.bbc.view.activities.ui.model.InShortsNews
import com.buildweek.bbc.view.activities.ui.recyclerviews.InshortsRecyclerAdapter
import com.buildweek.bbc.view.activities.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_africa.*
import retrofit2.Call
import retrofit2.Response


//aditya
class AfricaFragment :Fragment(){

    lateinit var viewModel : MainViewModel
    lateinit var adapter : InshortsRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_africa, container, false)

        val news = RetrofitBuilder.newsInstance.getNews("africa")
        news.enqueue(object : retrofit2.Callback<InShortsNews> {
            override fun onFailure(call: Call<InShortsNews>, t: Throwable) {
                Log.d("Sumeet","Error in fetching news",t)
            }

            override fun onResponse(call: Call<InShortsNews>, response: Response<InShortsNews>) {
                val news = response.body()
                if(news!=null){
                    adapter = InshortsRecyclerAdapter(context!!, news.data)
                    inShotsRecyclerView.adapter = adapter
                    inShotsRecyclerView.layoutManager = LinearLayoutManager(context)
                }
            }

        })
        return root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroy() {
        super.onDestroy()

    }
}