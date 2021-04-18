package com.buildweek.bbc.view.activities.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.buildweek.bbc.R
import com.buildweek.bbc.view.activities.ui.recyclerviews.InshortsRecyclerAdapter
import com.buildweek.bbc.view.activities.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_africa.*

class ScienceFragment :Fragment(){

    lateinit var viewModel : MainViewModel
    lateinit var adapter : InshortsRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_science, container, false)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.newsByCategory("Science")
        viewModel.getLocalServerNews().observe(viewLifecycleOwner, Observer {
            adapter = context?.let { it1 -> InshortsRecyclerAdapter(it1,it) }!!
            inShotsRecyclerView.adapter = adapter
            inShotsRecyclerView.layoutManager = LinearLayoutManager(context)
        })

        return root
    }
}