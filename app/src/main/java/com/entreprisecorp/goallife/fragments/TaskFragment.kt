package com.entreprisecorp.goallife.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.entreprisecorp.goallife.R
import com.entreprisecorp.goallife.Task
import com.entreprisecorp.goallife.TaskAdapter
import kotlinx.android.synthetic.main.fragment_today.view.*


class TaskFragment : Fragment(), TaskAdapter.OnItemClickListener {
    private val listTask = ArrayList<Task>()
    private val adapterTask  = TaskAdapter(listTask, this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task, container, false)

        listTask.add(Task("Faire une séance de sport", "Jambes + les bibis", Task.Frequency.WEEKLY))
        listTask.add(Task("Lire un livre", "", Task.Frequency.MONTHLY))
        listTask.add(Task("Regarder les mails", "", Task.Frequency.DAILY))
        listTask.add(Task("Appeller mamie", "le soir avant 19h", Task.Frequency.MONTHLY))
        listTask.add(Task("Appeller les darons", "avant 20 heures", Task.Frequency.WEEKLY))

        Toast.makeText(context, listTask[0].definition, Toast.LENGTH_SHORT).show()
        adapterTask.notifyDataSetChanged()
        view.recyclerviewTask.adapter = adapterTask
        view.recyclerviewTask.layoutManager = LinearLayoutManager(context)
        view.recyclerviewTask.setHasFixedSize(true)

        return view
    }
    override fun onItemClick(position: Int) {
        Toast.makeText(context, "Item $position clicked", Toast.LENGTH_SHORT).show()
        val clickedItem = listTask[position]
        clickedItem.definition = "Clicked"
        adapterTask.notifyItemChanged(position)
    }


}