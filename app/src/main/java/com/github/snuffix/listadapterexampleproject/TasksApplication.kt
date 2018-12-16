package com.github.snuffix.listadapterexampleproject

import android.app.Application
import com.github.snuffix.listadapterexampleproject.kodein.appModule
import com.github.snuffix.listadapterexampleproject.kodein.dataModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

class TasksApplication : Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(dataModule)
        import(appModule)
    }
}