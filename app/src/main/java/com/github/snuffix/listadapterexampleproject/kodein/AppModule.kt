package com.github.snuffix.listadapterexampleproject.kodein

import com.github.snuffix.listadapterexampleproject.viewmodel.TasksViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

val appModule = Kodein.Module("appModule") {
    bind() from provider { TasksViewModelFactory(instance()) }
}
