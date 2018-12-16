package com.github.snuffix.listadapterexampleproject.db

import androidx.lifecycle.LiveData
import com.github.snuffix.listadapterexampleproject.model.Task


interface TasksDao {
    fun addTask(task: Task)
    fun getTasks(): LiveData<List<Task>>
    fun deleteTask(id: String)
}