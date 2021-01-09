package com.entreprisecorp.goallife.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.entreprisecorp.goallife.*
import kotlinx.android.synthetic.main.fragment_today.*
import kotlinx.android.synthetic.main.fragment_today.view.*

class TodayFragment : Fragment(), TodayAdapter.OnItemClickListener {

    private val adapterTask  = TodayAdapter(App.listTask, this, App.applicationContext())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_today, container, false)



        Toast.makeText(context, App.listTask[0].definition, Toast.LENGTH_SHORT).show()
        adapterTask.notifyDataSetChanged()
        view.recyclerviewTask.adapter = adapterTask
        view.recyclerviewTask.layoutManager = LinearLayoutManager(context)
        view.recyclerviewTask.setHasFixedSize(true)

        // Inflate the layout for this fragment
        return view
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(context, "Item $position clicked", Toast.LENGTH_SHORT).show()
        val clickedItem = App.listTask[position]
        clickedItem.definition = "Clicked"
        adapterTask.notifyItemChanged(position)
    }

}