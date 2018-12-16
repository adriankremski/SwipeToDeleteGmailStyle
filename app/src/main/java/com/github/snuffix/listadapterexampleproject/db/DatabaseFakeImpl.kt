package com.github.snuffix.listadapterexampleproject.db


class DatabaseFakeImpl : Database {
    override val tasksDao: TasksDao = TasksDaoFakeImpl()
}