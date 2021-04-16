package com.buildweek.bbc.view.activities.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.buildweek.bbc.R
import com.buildweek.bbc.view.activities.ui.recyclerviews.InshortsRecyclerAdapter
import com.buildweek.bbc.view.activities.ui.recyclerviews.LocalServerRecyclerAdapter
import com.buildweek.bbc.view.activities.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_africa.*

class TopStoriesFragment : Fragment() {

    lateinit var viewModel: MainViewModel
    lateinit var adapter: LocalServerRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.worldNews()
        viewModel.getLocalServerNews().observe(viewLifecycleOwner, Observer {
            adapter = context?.let { it1 -> LocalServerRecyclerAdapter(it1, it) }!!
            inShotsRecyclerView.adapter = adapter
            inShotsRecyclerView.layoutManager = LinearLayoutManager(context)
        })

        return inflater.inflate(R.layout.fragment_top_stories, container, false)
    }

    companion object {
        fun newInstance() = TopStoriesFragment()
    }

}