package com.buildweek.bbc.clone.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.buildweek.bbc.R
import com.buildweek.bbc.clone.ui.activities.DetailedNewsViewActivity
import com.buildweek.bbc.clone.data.remote.model.springboot.LocalServerNewsItem
import com.buildweek.bbc.clone.ui.adapters.LocalServerRecyclerAdapter
import com.buildweek.bbc.clone.ui.adapters.NewsListRecyclerAdapter
import com.buildweek.bbc.clone.util.Resource
import com.buildweek.bbc.clone.viewmodel.MainViewModel
import com.buildweek.bbc.databinding.FragmentTopStoriesBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_africa.*
import kotlinx.android.synthetic.main.fragment_top_stories.*

@AndroidEntryPoint
class TopStoriesFragment : Fragment() {

    private lateinit var binding : FragmentTopStoriesBinding
    lateinit var viewModel: MainViewModel
    lateinit var newsListAdapter : NewsListRecyclerAdapter
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var progressBar: ProgressBar
    lateinit var youTubePlayerView: YouTubePlayerView

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
        swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.topNewsSwipeRefreshLayout)
        progressBar = view.findViewById<ProgressBar>(R.id.topNewsProgressBar)
        youTubePlayerView = view.findViewById<YouTubePlayerView>(R.id.youtubePlayer1)

        setRecyclerView()

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.getAllNews()
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun setRecyclerView() {

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.getAllNews()

        newsListAdapter = NewsListRecyclerAdapter()

        binding.inShotsRecyclerViewTopStories.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsListAdapter
        }

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
                    Toast.makeText(context,"${it.message}",Toast.LENGTH_SHORT).show()
                }
            }
        })

//        viewModel.worldNews()
//        viewModel.getLocalServerNews().observe(viewLifecycleOwner, Observer {
//            adapter = context?.let { it1 -> LocalServerRecyclerAdapter(it1, it, this) }!!
//            inShotsRecyclerViewTopStories.adapter = adapter
//            inShotsRecyclerViewTopStories.layoutManager = LinearLayoutManager(context)
//            progressBar.visibility = View.GONE
//            youTubePlayerView.visibility = View.VISIBLE
//
//            val layoutAnimationController: LayoutAnimationController =
//                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation)
//            inShotsRecyclerViewTopStories.layoutAnimation = layoutAnimationController
//            if(it == null)
//                inShotsRecyclerViewAfrica.visibility = View.GONE
//
//            adapter.notifyDataSetChanged()
//            inShotsRecyclerViewTopStories.scheduleLayoutAnimation()
//
//        })
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

//    override fun onItemClicked(article: LocalServerNewsItem) {
//        val intent = Intent(activity, DetailedNewsViewActivity::class.java)
//        intent.putExtra("article", article)
//        startActivity(intent)
//    }
}