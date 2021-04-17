package com.buildweek.bbc.view.activities.ui.recyclerviews

import android.content.Context
import android.content.res.loader.AssetsProvider
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.buildweek.bbc.R
import com.buildweek.bbc.view.activities.ui.model.LocalServerNewsItem
import com.bumptech.glide.Glide


class LocalServerRecyclerAdapter(val context: Context, val articles: List<LocalServerNewsItem>):
    RecyclerView.Adapter<LocalServerRecyclerAdapter.LocalServerRecyclerViewHolder>() {

    class LocalServerRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var newsImage = itemView.findViewById<ImageView>(R.id.inShotsNewsImage)
        var newsHeadline = itemView.findViewById<TextView>(R.id.inShotsNewsHeadline)
        var newsTime = itemView.findViewById<TextView>(R.id.inShotsNewsUploadedAt)
        var newsLocation = itemView.findViewById<TextView>(R.id.inShotsNewsLocation)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LocalServerRecyclerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_inshots_layout,parent,false)
        return LocalServerRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: LocalServerRecyclerViewHolder, position: Int) {
        val article = articles[position]
        val type = Typeface.createFromAsset(context.assets,"sanitrixiesans.ttf")
        holder.newsHeadline.typeface = type
        holder.newsHeadline.text = article.mainheading
        holder.newsLocation.text = article.region
        holder.newsTime.text = article.dateofnews
        Glide.with(context).load(article.image1).into(holder.newsImage)
    }
}