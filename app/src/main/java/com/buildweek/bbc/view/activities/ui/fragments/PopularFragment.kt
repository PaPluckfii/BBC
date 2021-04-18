package com.buildweek.bbc.view.activities.ui.fragments

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
import com.buildweek.bbc.view.activities.ui.activities.DetailedNewsViewActivity
import com.buildweek.bbc.view.activities.ui.model.LocalServerNewsItem
import com.buildweek.bbc.view.activities.ui.recyclerviews.LocalServerRecyclerAdapter
import com.buildweek.bbc.view.activities.ui.viewmodel.MainViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.android.synthetic.main.fragment_africa.*


class PopularFragment : Fragment() ,LocalServerRecyclerAdapter.OnItemClickListener{

    lateinit var viewModel: MainViewModel
    lateinit var adapter: LocalServerRecyclerAdapter
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular, container, false)
    }
    companion object {
        fun newInstance() = PopularFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.topNewsSwipeRefreshLayout)
        progressBar = view.findViewById<ProgressBar>(R.id.topNewsProgressBar)



        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.worldNews()
        viewModel.getLocalServerNews().observe(viewLifecycleOwner, Observer {
            adapter = context?.let { it1 -> LocalServerRecyclerAdapter(it1, it, this) }!!
            inShotsRecyclerView.adapter = adapter
            inShotsRecyclerView.layoutManager = LinearLayoutManager(context)

            val layoutAnimationController: LayoutAnimationController = AnimationUtils.loadLayoutAnimation(context,R.anim.layout_animation)
            inShotsRecyclerView.layoutAnimation = layoutAnimationController
            adapter.notifyDataSetChanged()
            inShotsRecyclerView.scheduleLayoutAnimation()

        })

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.worldNews()
            viewModel.getLocalServerNews().observe(viewLifecycleOwner, Observer {
                adapter = context?.let { it1 -> LocalServerRecyclerAdapter(it1, it, this) }!!
                inShotsRecyclerView.adapter = adapter
                inShotsRecyclerView.layoutManager = LinearLayoutManager(context)
            })
            adapter.notifyDataSetChanged()
            swipeRefreshLayout.isRefreshing = false
        }
    }

    override fun onItemClicked(article: LocalServerNewsItem) {
        val intent = Intent(activity, DetailedNewsViewActivity::class.java)
        intent.putExtra("article",article)
        startActivity(intent)
    }

}