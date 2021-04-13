package com.buildweek.bbc.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.buildweek.bbc.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var toggle:ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toggle= ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                    R.id.mitem1->Toast.makeText(applicationContext,
                    "clicked item 1",Toast.LENGTH_LONG).show()
                R.id.mitem2->Toast.makeText(applicationContext,
                    "clicked item 2",Toast.LENGTH_LONG).show()
                R.id.mitem2->Toast.makeText(applicationContext,
                    "clicked item 3",Toast.LENGTH_LONG).show()
            }
            true
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return false
        }
        return super.onContextItemSelected(item)
    }
}