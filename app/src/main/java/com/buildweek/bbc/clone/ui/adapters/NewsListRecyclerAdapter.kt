package com.buildweek.bbc.clone.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.buildweek.bbc.clone.data.remote.model.opensourceapi.Article
import com.buildweek.bbc.databinding.NewsItemLayoutBinding
import com.bumptech.glide.Glide

class NewsListRecyclerAdapter() : RecyclerView.Adapter<NewsListRecyclerAdapter.NewsListViewHolder>() {

    inner class NewsListViewHolder(
        private val binding : NewsItemLayoutBinding
        ) : RecyclerView.ViewHolder(binding.root) {

        fun setData(article: Article) {
            binding.inShotsNewsHeadline.text = article.title
            binding.inShotsNewsLocation.text = article.author
            binding.inShotsNewsUploadedAt.text = article.publishedAt
            Glide.with(binding.root).load(article.urlToImage).into(binding.inShotsNewsImage)
        }

    }

    /**
     * Diff Util Callback to compare old and new list
     */
    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == oldItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        val binding = NewsItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NewsListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.setData(article)
    }
}