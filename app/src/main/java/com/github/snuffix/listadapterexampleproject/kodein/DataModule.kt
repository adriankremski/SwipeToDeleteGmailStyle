package com.github.snuffix.listadapterexampleproject.kodein

import com.github.snuffix.listadapterexampleproject.db.Database
import com.github.snuffix.listadapterexampleproject.db.DatabaseFakeImpl
import com.github.snuffix.listadapterexampleproject.db.TasksDao
import com.github.snuffix.listadapterexampleproject.repository.TasksRepository
import com.github.snuffix.listadapterexampleproject.repository.TasksRepositoryImpl
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton


val dataModule = Kodein.Module("dataModule") {
    bind<Database>() with singleton { DatabaseFakeImpl() }
    bind<TasksDao>() with singleton { instance<Database>().tasksDao }
    bind<TasksRepository>() with singleton { TasksRepositoryImpl(instance()) }
}