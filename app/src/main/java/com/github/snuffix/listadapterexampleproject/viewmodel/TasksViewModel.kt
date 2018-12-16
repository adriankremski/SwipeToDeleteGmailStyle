package com.github.snuffix.listadapterexampleproject.viewmodel

import androidx.lifecycle.ViewModel
import com.github.snuffix.listadapterexampleproject.model.Task
import com.github.snuffix.listadapterexampleproject.repository.TasksRepository


class TasksViewModel(private val tasksRepository: TasksRepository)
    : ViewModel() {

    fun addTask(task: Task) = tasksRepository.addTask(task)

    fun getTasks() = tasksRepository.getTasks()

    fun deleteTask(id: String) {
        tasksRepository.deleteTask(id)
    }
}