package com.buildweek.bbc.view.activities.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.buildweek.bbc.R
import com.buildweek.bbc.view.activities.ui.viewpageradapter.HomeViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_world.*


class WorldFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_world, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPagerAdapter = HomeViewPagerAdapter(this)
        viewPagerHome.adapter = viewPagerAdapter

        TabLayoutMediator(tabLayoutHome, viewPagerHome) { tab, position ->
            tab.text = when (position) {
                0 -> "Top Stories"
                1 -> "Video"
                2 -> "My News"
                3 -> "Popular"
                4 -> "LIVE"
                else -> "Top Stories"
            }
        }.attach()
    }
}