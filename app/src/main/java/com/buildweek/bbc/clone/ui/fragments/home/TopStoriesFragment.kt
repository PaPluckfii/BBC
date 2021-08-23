package com.buildweek.bbc.clone.ui.fragments.home

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
import com.buildweek.bbc.clone.data.remote.model.opensourceapi.Article
import com.buildweek.bbc.clone.ui.activities.DetailedNewsViewActivity
import com.buildweek.bbc.clone.ui.adapters.NewsItemClickListener
import com.buildweek.bbc.clone.ui.adapters.NewsListRecyclerAdapter
import com.buildweek.bbc.clone.util.Resource
import com.buildweek.bbc.clone.viewmodel.MainViewModel
import com.buildweek.bbc.databinding.FragmentTopStoriesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopStoriesFragment : Fragment() , NewsItemClickListener {

    private lateinit var binding : FragmentTopStoriesBinding
    private val viewModel: MainViewModel by viewModels()
    lateinit var newsListAdapter : NewsListRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTopStoriesBinding.inflate(layoutInflater)
        return binding.root
    }

    companion object {
        fun newInstance() = TopStoriesFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllNews(
            "",
            "",
            "",
            "breaking"
        )
        setRecyclerView()
        observeLiveData()
        handleListeners()
    }

    private fun setRecyclerView() {

        newsListAdapter = NewsListRecyclerAdapter(this)

        binding.inShotsRecyclerViewTopStories.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsListAdapter
        }

    }

    private fun observeLiveData() {
        viewModel.newsList.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Success -> {
                    hideProgressBar()
                    newsListAdapter.differ.submitList(it.data?.articles)
                }
                is Resource.Error -> {
                    hideProgressBar()
                    Toast.makeText(context,"Oops something went wrong!",Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun handleListeners(){
        binding.topNewsSwipeRefreshLayout.setOnRefreshListener {
            viewModel.getAllNews(
                "",
                "",
                "",
                "breaking"
            )
            binding.topNewsSwipeRefreshLayout.isRefreshing = false
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
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
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
            val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastPage && isNotAtBeginning &&
                    isTotalMoreThanVisible && isScrolling

            if(shouldPaginate) {
                viewModel.getAllNews(
                    "",
                    "",
                    "",
                    "breaking"
                )
                isScrolling = false
            }
        }
    }

    private fun showProgressBar() {
        binding.topNewsProgressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.topNewsProgressBar.visibility = View.GONE
    }

    override fun onNewsItemClicked(article : Article) {
        val intent = Intent(activity, DetailedNewsViewActivity::class.java)
        intent.putExtra("article", article)
        startActivity(intent)
    }
}