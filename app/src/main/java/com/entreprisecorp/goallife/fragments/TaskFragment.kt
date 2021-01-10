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
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.entreprisecorp.goallife.*
import kotlinx.android.synthetic.main.fragment_task.view.*
import kotlinx.android.synthetic.main.pop_up_layout.*
import kotlinx.android.synthetic.main.pop_up_layout.view.*
import java.lang.reflect.Modifier


class TaskFragment : Fragment(), TaskAdapter.OnItemClickListener {

    private val adapterTask  = TaskAdapter(App.listTask, this)
    val db =  DataBaseHelper(App.applicationContext())

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task, container, false)

        view.recyclerviewTask.adapter = adapterTask
        view.recyclerviewTask.layoutManager = LinearLayoutManager(context)
        view.recyclerviewTask.setHasFixedSize(true)


        /*val tastTodadd = Task("testWeek","test", Task.Frequency.WEEKLY)
        tastTodadd.dateDone = "01/01/2021"
        db.insertData(tastTodadd)

        val tastTodadd1 = Task("testDays07","test", Task.Frequency.DAILY)
        tastTodadd1.dateDone = "05/01/2021"
        db.insertData(tastTodadd1)

        val tastTodadd2 = Task("testDays","test", Task.Frequency.DAILY)
        tastTodadd2.dateDone = "09/01/2021"
        db.insertData(tastTodadd2)
        db.readData()
        adapterTask.notifyDataSetChanged()*/


        view.buttonAddTast.setOnClickListener(){
            showPopup(view)
        }
        return view

    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onItemClick(position: Int) {
        view?.let { showPopupModifTask(it, App.listTask[position], position) }
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

        val task = Task("","", Task.Frequency.DAILY)
        viewP.buttonAdd.setOnClickListener(){
            task.objective =  viewP.nameText.text.toString()
            task.definition = viewP.deffText.text.toString()

            /*App.listTask += task*/

            db.insertData(task)
            App.listTask = db.readData()
            adapterTask.notifyDataSetChanged()
            window.dismiss()
        }


        viewP.radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radioButton -> {
                    task.frequency = Task.Frequency.DAILY
                }
                R.id.radioButton2 -> {
                    task.frequency = Task.Frequency.WEEKLY
                }
                else -> {
                    task.frequency = Task.Frequency.MONTHLY
                }
            }
        }

        viewP.backFrame.setOnClickListener(){
            window.dismiss()
        }
        window.showAtLocation( view,Gravity.CENTER, 0,0)



    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ResourceType")
    private fun showPopupModifTask(view: View, task : Task, position: Int){
        val window = PopupWindow(context)
        val viewP = layoutInflater.inflate(R.layout.pop_up_layout, null)
        window.width = LinearLayout.LayoutParams.MATCH_PARENT;
        window.height = LinearLayout.LayoutParams.MATCH_PARENT;
        window.isFocusable = true
        viewP.elevation = 5.0F;
        window.contentView = viewP

        viewP.supprButton.isVisible = true
        viewP.titlepopup2.text = "Modifier un objectif"
        viewP.buttonAdd.text = "Modifier"
        viewP.nameText.setText(task.objective)
        viewP.deffText.setText(task.definition)


        when (task.frequency) {
            Task.Frequency.DAILY -> {
                viewP.radioButton.isChecked = true
            }
            Task.Frequency.WEEKLY -> {
                viewP.radioButton2.isChecked = true
            }
            else -> {
                viewP.radioButton3.isChecked = true
            }
        }

        viewP.buttonAdd.setOnClickListener(){
            task.objective =  viewP.nameText.text.toString()
            task.definition = viewP.deffText.text.toString()

            task.resetDate()
            db.modifTask(task)
            db.readData()
            adapterTask.notifyItemChanged(position)
            window.dismiss()
        }

        viewP.supprButton.setOnClickListener(){
            db.supprTast(task)
            db.readData()
            adapterTask.notifyDataSetChanged()
            window.dismiss()
        }


        viewP.radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radioButton -> {
                    task.frequency = Task.Frequency.DAILY
                }
                R.id.radioButton2 -> {
                    task.frequency = Task.Frequency.WEEKLY
                }
                else -> {
                    task.frequency = Task.Frequency.MONTHLY
                }
            }
        }

        viewP.backFrame.setOnClickListener(){
            window.dismiss()
        }
        window.showAtLocation( view,Gravity.CENTER, 0,0)



    }



}