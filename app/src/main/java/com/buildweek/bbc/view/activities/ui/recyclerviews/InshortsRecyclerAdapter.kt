package com.buildweek.bbc.view.activities.ui.recyclerviews

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.buildweek.bbc.R
import com.buildweek.bbc.view.activities.ui.model.LocalServerNews
import com.bumptech.glide.Glide
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class InshortsRecyclerAdapter(val context: Context, val articles: LocalServerNews) :
    RecyclerView.Adapter<InshortsRecyclerAdapter.InshortsRecyclerViewHolder>() {

    class InshortsRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var newsImage = itemView.findViewById<ImageView>(R.id.inShotsNewsImage)
        var newsHeadline = itemView.findViewById<TextView>(R.id.inShotsNewsHeadline)
        var inShotsNewsLocation = itemView.findViewById<TextView>(R.id.inShotsNewsLocation)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InshortsRecyclerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_inshots_layout, parent, false)
        return InshortsRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: InshortsRecyclerViewHolder, position: Int) {
        val article = articles[position]
        holder.newsHeadline.text = article.mainheading
        holder.inShotsNewsLocation.text = article.region
        Glide.with(context).load(article.frontimage).into(holder.newsImage)
    }
}