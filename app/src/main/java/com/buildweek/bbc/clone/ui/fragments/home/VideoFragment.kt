package com.buildweek.bbc.clone.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.buildweek.bbc.R
import com.buildweek.bbc.clone.ui.adapters.NewsListRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideoFragment : Fragment() {

    lateinit var adapter : NewsListRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video, container, false)
    }
    companion object {
        fun newInstance() = VideoFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}