package com.github.snuffix.listadapterexampleproject.db

import androidx.lifecycle.MutableLiveData
import com.github.snuffix.listadapterexampleproject.model.Task


class TasksDaoFakeImpl : TasksDao {
    private val tasksList = mutableListOf<Task>()
    private val tasks = MutableLiveData<List<Task>>()

    init {
        tasks.value = tasksList
    }

    override fun addTask(task: Task) {
        tasksList.add(task)
        tasks.postValue(tasksList)
    }

    override fun getTasks() = tasks

    override fun deleteTask(id: String) {
        tasksList.removeAll { it.id == id }
        tasks.postValue(tasksList)
    }
}