package com.buildweek.bbc.clone.ui.fragments.category

import android.os.Bundle
import android.view.View
import com.buildweek.bbc.R
import com.buildweek.bbc.clone.ui.fragments.NewsArticleFragment


class AthleticsFragment : NewsArticleFragment(R.layout.fragment_atheletics) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentNews()
    }

    override fun currentNews() {
        viewModel.getAllNews(
            "",
            "",
            "",
            "india"
        )
    }

}