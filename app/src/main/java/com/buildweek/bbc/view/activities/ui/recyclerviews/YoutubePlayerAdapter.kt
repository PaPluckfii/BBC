package com.buildweek.bbc.view.activities.ui.recyclerviews

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.buildweek.bbc.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.android.synthetic.main.item_video_layout.view.*

class YoutubePlayerAdapter(val context: Context) : RecyclerView.Adapter<YoutubePlayerAdapter.YoutubePlayerViewHolder>() {

    class YoutubePlayerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var videoPlayer = itemView.findViewById<YouTubePlayerView>(R.id.ytVideoPlayer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YoutubePlayerViewHolder {

    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: YoutubePlayerViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}