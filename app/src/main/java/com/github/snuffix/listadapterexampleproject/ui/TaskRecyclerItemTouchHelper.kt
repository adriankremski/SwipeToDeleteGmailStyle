package com.github.snuffix.listadapterexampleproject.ui

import android.graphics.Canvas
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ItemTouchHelper
import com.github.snuffix.listadapterexampleproject.model.Task


class TaskRecyclerItemTouchHelper(
    dragDirs: Int, swipeDirs: Int, private val listener: ItemTouchHelperListener
) : ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return true
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        (viewHolder as? TaskListAdapter.TaskViewHolder)?.apply {
            getDefaultUIUtil().onSelected(viewForeground)
        }
    }

    override fun onChildDrawOver(
        c: Canvas, recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float,
        actionState: Int, isCurrentlyActive: Boolean
    ) {
        val viewHolder = (viewHolder as TaskListAdapter.TaskViewHolder)

        if (dX < 0) {
            viewHolder.deleteMode()
        } else {
            viewHolder.editMode()
        }

        val foregroundView = viewHolder.viewForeground
        getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive)
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        (viewHolder as TaskListAdapter.TaskViewHolder).apply {
            defaultMode()
            getDefaultUIUtil().clearView(viewForeground)
        }
    }

    override fun onChildDraw(
        c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float,
        dY: Float, actionState: Int, isCurrentlyActive: Boolean
    ) {
        val viewHolder = (viewHolder as TaskListAdapter.TaskViewHolder)

        if (dX < 0) {
            viewHolder.deleteMode()
        } else {
            viewHolder.editMode()
        }

        val foregroundView = viewHolder.viewForeground
        getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val taskViewHolder = viewHolder as TaskListAdapter.TaskViewHolder

        when (direction) {
            ItemTouchHelper.LEFT -> listener.onDeleteItem(taskViewHolder.task)
            ItemTouchHelper.RIGHT -> listener.onEditItem(taskViewHolder.task)
        }
    }

    interface ItemTouchHelperListener {
        fun onDeleteItem(task: Task)
        fun onEditItem(task: Task)
    }
}