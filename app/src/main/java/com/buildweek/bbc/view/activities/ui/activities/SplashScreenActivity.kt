package com.buildweek.bbc.view.activities.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.buildweek.bbc.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splac_screen)

        Handler().postDelayed({
            val  intent = Intent(this@SplashScreenActivity, HomeActivity::class.java)
            startActivity(intent)
        },3000)
    }
}