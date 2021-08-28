package com.buildweek.bbc.clone.ui.activities

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.buildweek.bbc.R
import com.buildweek.bbc.clone.data.remote.model.opensourceapi.Article
import com.buildweek.bbc.clone.data.remote.model.springboot.LocalServerNewsItem
import com.bumptech.glide.Glide

class DetailedNewsViewActivity : AppCompatActivity() {

    lateinit var article : Article

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_news_view)
        article = intent.getSerializableExtra("article") as Article

        val heading = findViewById<TextView>(R.id.tvNewsHeadline)
        val subHeading = findViewById<TextView>(R.id.tvNewsSubHeadline)
        val author = findViewById<TextView>(R.id.tvNewsAuthor)
        val location = findViewById<TextView>(R.id.tvNewsLocation)
        val date = findViewById<TextView>(R.id.tvNewsDate)
        val content = findViewById<TextView>(R.id.tvNewsContent)
        val image1 = findViewById<ImageView>(R.id.ivImage1)
        val image2 = findViewById<ImageView>(R.id.ivImage2)

        val type = Typeface.createFromAsset(assets,"sanitrixiesans.ttf")

        heading.text = article.title
        subHeading.text = article.description
        author.text = article.author
        date.text = article.publishedAt
        content.text = article.content
        Glide.with(this).load(article.urlToImage).into(image1)

        heading.typeface = type
        subHeading.typeface = type
        author.typeface = type
        location.typeface = type
        date.typeface = type
        content.typeface = type
    }
}