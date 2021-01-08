package com.entreprisecorp.goallife.fragments

import android.annotation.SuppressLint
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.entreprisecorp.goallife.R
import com.entreprisecorp.goallife.Task
import com.entreprisecorp.goallife.TaskAdapter
import kotlinx.android.synthetic.main.fragment_task.view.*
import kotlinx.android.synthetic.main.pop_up_layout.*
import kotlinx.android.synthetic.main.pop_up_layout.view.*


class TaskFragment : Fragment(), TaskAdapter.OnItemClickListener {
    private val listTask = ArrayList<Task>()
    private val adapterTask  = TaskAdapter(listTask, this)

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
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

        view.buttonAddTast.setOnClickListener(){
            showPopup(view)
        }
        return view

    }
    override fun onItemClick(position: Int) {
        Toast.makeText(context, "Item $position clicked", Toast.LENGTH_SHORT).show()
        val clickedItem = listTask[position]
        clickedItem.definition = "Clicked"
        adapterTask.notifyItemChanged(position)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ResourceType")
    private fun showPopup(view: View){
        val window = PopupWindow(context)
        val viewP = layoutInflater.inflate(R.layout.pop_up_layout, null)
        window.width = LinearLayout.LayoutParams.MATCH_PARENT;
        window.height = LinearLayout.LayoutParams.MATCH_PARENT;
        window.isFocusable = true
        viewP.elevation = 5.0F;
        window.contentView = viewP

        viewP.buttonAdd.setOnClickListener(){
            window.dismiss()
            val task = Task(viewP.nameText.text.toString(), viewP.deffText.text.toString(), Task.Frequency.WEEKLY)
            listTask += task
            adapterTask.notifyDataSetChanged()
        }

        viewP.backFrame.setOnClickListener(){
            window.dismiss()
        }
        window.showAtLocation( view,Gravity.CENTER, 0,0)



    }


}