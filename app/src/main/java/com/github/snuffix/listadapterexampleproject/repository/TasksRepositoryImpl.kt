package com.github.snuffix.listadapterexampleproject.repository

import com.github.snuffix.listadapterexampleproject.db.TasksDao
import com.github.snuffix.listadapterexampleproject.model.Task


class TasksRepositoryImpl(private val tasksDao: TasksDao) : TasksRepository {
    override fun addTask(task: Task) {
        tasksDao.addTask(task)
    }

    override fun getTasks() = tasksDao.getTasks()

    override fun deleteTask(id: String) {
        tasksDao.deleteTask(id)
    }
}