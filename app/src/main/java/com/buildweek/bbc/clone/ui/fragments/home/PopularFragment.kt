package com.buildweek.bbc.clone.ui.fragments.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.buildweek.bbc.R
import com.buildweek.bbc.clone.ui.activities.DetailedNewsViewActivity
import com.buildweek.bbc.clone.data.remote.model.springboot.LocalServerNewsItem
import com.buildweek.bbc.clone.ui.adapters.LocalServerRecyclerAdapter
import com.buildweek.bbc.clone.ui.fragments.NewsArticleFragment
import com.buildweek.bbc.clone.viewmodel.MainViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_popular.*

@AndroidEntryPoint
class PopularFragment : NewsArticleFragment(R.layout.fragment_popular){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentNews()
    }

    override fun currentNews() {
        viewModel.getAllNews(
            "",
            "breaking",
            "",
            ""
        )
    }

    companion object{
        fun newInstance() = PopularFragment()
    }
}