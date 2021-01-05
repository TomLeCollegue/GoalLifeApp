package com.entreprisecorp.goallife.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.entreprisecorp.goallife.R
import kotlinx.android.synthetic.main.fragment_today.*
import kotlinx.android.synthetic.main.fragment_today.view.*

class TodayFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_today, container, false)

        view.textmain.text = "lololol"
        // Inflate the layout for this fragment
        return view
    }

}