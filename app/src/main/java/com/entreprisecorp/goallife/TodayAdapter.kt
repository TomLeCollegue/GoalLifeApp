package com.entreprisecorp.goallife

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.task_list_item.view.*
import kotlinx.android.synthetic.main.task_today_item.view.*
import kotlinx.android.synthetic.main.task_today_item.view.taskDef
import kotlinx.android.synthetic.main.task_today_item.view.taskName

class TodayAdapter(
    private val list: List<Task>,
    private val listener: OnItemClickListener,
    private val context: Context
) :
    RecyclerView.Adapter<TodayAdapter.TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.task_today_item,
            parent, false)
        return TaskViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentItem = list[position]
        holder.taskName.text = currentItem.objective
        holder.taskDef.text = currentItem.definition
        holder.taskDate.text = currentItem.done

        when (currentItem.frequency) {
            Task.Frequency.DAILY -> {
                holder.taskImage.setImageResource(R.drawable.daily)
            }
            Task.Frequency.WEEKLY -> {
                holder.taskImage.setImageResource(R.drawable.weekly)
            }
            else -> {
                holder.taskImage.setImageResource(R.drawable.monthly)
            }
        }
        

    }
    override fun getItemCount() = list.size
    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        val taskImage : ImageView = itemView.imageViewItemToday
        val taskName: TextView = itemView.taskName
        val taskDef: TextView = itemView.taskDef
        val checkBox : ImageView = itemView.buttonDone
        val taskDate : TextView = itemView.textDate


        init {
            itemView.setOnClickListener(this)
            itemView.buttonDone.setOnClickListener(){
                list[adapterPosition].definition = "blabla"
                notifyDataSetChanged()
            }
        }
        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}