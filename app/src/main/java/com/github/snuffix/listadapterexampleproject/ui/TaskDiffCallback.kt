package com.github.snuffix.listadapterexampleproject.ui

import androidx.recyclerview.widget.DiffUtil
import com.github.snuffix.listadapterexampleproject.model.Task


class TaskDiffCallback : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem?.id == newItem?.id
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }
}