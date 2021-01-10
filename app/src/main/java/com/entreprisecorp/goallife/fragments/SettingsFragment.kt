package com.entreprisecorp.goallife.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.entreprisecorp.goallife.R
import com.entreprisecorp.goallife.Task
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.fragment_settings.view.*

class SettingsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        view.timeEdit.setText(Task.today)

        view.buttonChangeTime.setOnClickListener(){
            Task.today = timeEdit.text.toString()
        }

        view.switch1.isChecked = true
        return view
    }


}