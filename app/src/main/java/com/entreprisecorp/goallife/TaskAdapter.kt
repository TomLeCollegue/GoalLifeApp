package com.entreprisecorp.goallife

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.task_list_item.view.*
import kotlinx.android.synthetic.main.task_today_item.view.*
import kotlinx.android.synthetic.main.task_today_item.view.taskDef
import kotlinx.android.synthetic.main.task_today_item.view.taskName

class TaskAdapter(
    private val list: List<Task>,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.task_list_item,
            parent, false)
        return TaskViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentItem = list[position]
        holder.taskName.text = currentItem.objective
        holder.taskDef.text = currentItem.definition

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

        val taskImage : ImageView = itemView.imageViewItem
        val taskName: TextView = itemView.taskName
        val taskDef: TextView = itemView.taskDef
        init {
            itemView.setOnClickListener(this)
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