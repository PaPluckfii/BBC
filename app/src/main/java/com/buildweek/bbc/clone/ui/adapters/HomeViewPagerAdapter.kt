package com.buildweek.bbc.clone.ui.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.buildweek.bbc.clone.ui.fragments.*
import com.buildweek.bbc.clone.ui.fragments.home.*

class HomeViewPagerAdapter(fm: WorldFragment) : FragmentStateAdapter(fm) {
    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> return TopStoriesFragment.newInstance()
            1 -> return VideoFragment.newInstance()
            2 -> return MyNewsFragment.newInstance()
            3 -> return PopularFragment.newInstance()
            4 -> return LiveFragment.newInstance()
            else -> TopStoriesFragment.newInstance()
        }
    }
}