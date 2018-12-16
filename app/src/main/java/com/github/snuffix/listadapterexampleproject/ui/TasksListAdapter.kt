package com.github.snuffix.listadapterexampleproject.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.snuffix.listadapterexampleproject.R
import com.github.snuffix.listadapterexampleproject.model.Task
import kotlinx.android.synthetic.main.item_swipeable_background.view.*
import kotlinx.android.synthetic.main.item_task_row.view.*


class TaskListAdapter(private val clickListener: (Task) -> Unit) :
    ListAdapter<Task, TaskListAdapter.TaskViewHolder>(TaskDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TaskViewHolder(
            inflater.inflate(
                R.layout.item_task_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val viewForeground: View
        get() = itemView.viewForeground

        lateinit var task: Task

        fun bind(task: Task, clickListener: (Task) -> Unit) {
            this.task = task
            itemView.name.text = "${task.title}, ${task.id}}"
            itemView.setOnClickListener { clickListener(task) }
            defaultMode()
        }

        fun editMode() {
            if (itemView.viewDeleteBackground.visibility != View.GONE) {
                itemView.viewEditBackground.visibility = View.VISIBLE
                itemView.viewDeleteBackground.visibility = View.GONE
            }
        }

        fun deleteMode() {
            if (itemView.viewEditBackground.visibility != View.GONE) {
                itemView.viewDeleteBackground.visibility = View.VISIBLE
                itemView.viewEditBackground.visibility = View.GONE
            }
        }

        fun defaultMode() {
            itemView.viewForeground.visibility = View.VISIBLE
            itemView.viewDeleteBackground.visibility = View.VISIBLE
            itemView.viewEditBackground.visibility = View.VISIBLE
        }
    }
}