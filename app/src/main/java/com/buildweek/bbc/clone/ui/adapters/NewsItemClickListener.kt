package com.buildweek.bbc.clone.ui.adapters

import com.buildweek.bbc.clone.data.remote.model.opensourceapi.Article

interface NewsItemClickListener {
    fun onNewsItemClicked(article: Article)
}