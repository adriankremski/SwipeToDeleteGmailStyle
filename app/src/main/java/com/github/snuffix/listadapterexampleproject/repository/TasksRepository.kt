package com.github.snuffix.listadapterexampleproject.repository

import androidx.lifecycle.LiveData
import com.github.snuffix.listadapterexampleproject.model.Task


interface TasksRepository {
    fun addTask(task: Task)
    fun getTasks(): LiveData<List<Task>>
    fun deleteTask(id: String)
}