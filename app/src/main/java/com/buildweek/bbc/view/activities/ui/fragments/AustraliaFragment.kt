package com.buildweek.bbc.view.activities.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.buildweek.bbc.R


class AustraliaFragment :Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        
        val root = inflater.inflate(R.layout.fragment_australia, container, false)
        
        return root
    }
}