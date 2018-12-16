package com.github.snuffix.listadapterexampleproject

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DefaultItemAnimator
import com.github.snuffix.listadapterexampleproject.model.Task
import com.github.snuffix.listadapterexampleproject.ui.TaskListAdapter
import com.github.snuffix.listadapterexampleproject.ui.TaskRecyclerItemTouchHelper
import com.github.snuffix.listadapterexampleproject.viewmodel.TasksViewModel
import com.github.snuffix.listadapterexampleproject.viewmodel.TasksViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance


class MainActivity : AppCompatActivity(), TaskRecyclerItemTouchHelper.ItemTouchHelperListener, KodeinAware {
    override val kodein by closestKodein()

    private val viewModelFactory: TasksViewModelFactory by instance()
    private lateinit var viewModel: TasksViewModel
    private lateinit var adapter: TaskListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = TaskListAdapter {
            Toast.makeText(this, "Task ${it.title}", Toast.LENGTH_SHORT).show()
        }

        tasksRecycler.adapter = adapter
        tasksRecycler.layoutManager = LinearLayoutManager(this)

        tasksRecycler.itemAnimator = DefaultItemAnimator()
        tasksRecycler.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))


        val itemTouchHelperCallback = TaskRecyclerItemTouchHelper(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT,
            this
        )
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(tasksRecycler)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TasksViewModel::class.java)

        viewModel.getTasks().observe(this, Observer { tasks ->
            adapter.submitList(tasks.toMutableList())
        })

        addTask.setOnClickListener {
            var task = Task(title = "Task", description = "Task description")
            viewModel.addTask(task)
        }
    }

    override fun onDeleteItem(task: Task) {
        viewModel.deleteTask(task.id)
    }

    override fun onEditItem(task: Task) {
        viewModel.deleteTask(task.id)
    }
}
