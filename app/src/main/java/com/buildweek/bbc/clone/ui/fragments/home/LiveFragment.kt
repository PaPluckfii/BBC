package com.buildweek.bbc.clone.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.buildweek.bbc.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LiveFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_live, container, false)
    }
    companion object {
        fun newInstance() = LiveFragment()
    }

}