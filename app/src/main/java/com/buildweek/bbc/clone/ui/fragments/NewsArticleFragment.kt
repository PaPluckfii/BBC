package com.buildweek.bbc.clone.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.buildweek.bbc.R
import com.buildweek.bbc.clone.data.remote.model.opensourceapi.Article
import com.buildweek.bbc.clone.ui.activities.DetailedNewsViewActivity
import com.buildweek.bbc.clone.ui.adapters.NewsItemClickListener
import com.buildweek.bbc.clone.ui.adapters.NewsListRecyclerAdapter
import com.buildweek.bbc.clone.util.Resource
import com.buildweek.bbc.clone.viewmodel.MainViewModel
import com.buildweek.bbc.databinding.FragmentNewsArticleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class NewsArticleFragment(
    layoutId : Int
) : Fragment(layoutId), NewsItemClickListener {

    lateinit var binding: FragmentNewsArticleBinding
    lateinit var newsListAdapter: NewsListRecyclerAdapter
    val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentNewsArticleBinding.bind(view)

        setRecyclerView()
        observeLiveData()
        handleListeners()
    }

    fun setRecyclerView() {

        newsListAdapter = NewsListRecyclerAdapter(this)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsListAdapter
        }

    }

    fun observeLiveData() {
        viewModel.newsList.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Success -> {
                    hideProgressBar()
                    newsListAdapter.differ.submitList(it.data?.articles)
                }
                is Resource.Error -> {
                    hideProgressBar()
                    Toast.makeText(context, "Oops something went wrong!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }

    abstract fun currentNews()

    fun handleListeners() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.currentPage = 1
            currentNews()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    /**
     * Booleans for pagination
     */
    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    /**
     * Scroll listener for pagination
     */
    val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager

            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastPage = (firstVisibleItemPosition + visibleItemCount) >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= 10
            val shouldPaginate =
                isNotLoadingAndNotLastPage && isAtLastPage && isNotAtBeginning &&
                        isTotalMoreThanVisible && isScrolling

            if (shouldPaginate) {
                currentNews()
                isScrolling = false
            }
        }
    }

    fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    override fun onNewsItemClicked(article: Article) {
        val intent = Intent(activity, DetailedNewsViewActivity::class.java)
        intent.putExtra("article", article)
        startActivity(intent)
    }
}
