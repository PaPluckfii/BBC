package com.buildweek.bbc.clone.ui.fragments.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.buildweek.bbc.R
import com.buildweek.bbc.clone.ui.fragments.NewsArticleFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoriesFragment : NewsArticleFragment(R.layout.fragment_stories){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentNews()
    }

    override fun currentNews() {
        viewModel.getAllNews(
            "",
            "",
            "",
            "stories"
        )
    }
}