package com.buildweek.bbc.view.activities.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.buildweek.bbc.R
import com.buildweek.bbc.view.activities.ui.recyclerviews.InshortsRecyclerAdapter
import com.buildweek.bbc.view.activities.ui.recyclerviews.LocalServerRecyclerAdapter
import com.buildweek.bbc.view.activities.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_africa.*
import kotlinx.android.synthetic.main.fragment_top_stories.*
import kotlin.math.truncate

class TopStoriesFragment : Fragment() {

    lateinit var viewModel: MainViewModel
    lateinit var adapter: LocalServerRecyclerAdapter
    var isScrolling = false
    var page = 1
    var limit = 10


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_top_stories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.worldNews()
        viewModel.getLocalServerNews().observe(viewLifecycleOwner, Observer {
            adapter = context?.let { it1 -> LocalServerRecyclerAdapter(it1, it) }!!
            inShotsRecyclerViewTopStories.adapter = adapter
            inShotsRecyclerViewTopStories.layoutManager = LinearLayoutManager(context)

            inShotsRecyclerViewTopStories.addOnScrollListener(object :
                RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    
                }

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                        isScrolling = true
                    }

                }
            })
        })
    }

    companion object {
        fun newInstance() = TopStoriesFragment()
    }

}