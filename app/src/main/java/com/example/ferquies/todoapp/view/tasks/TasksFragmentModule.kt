package com.example.ferquies.todoapp.view.tasks

import android.content.Context
import android.support.v7.widget.helper.ItemTouchHelper
import com.example.ferquies.todoapp.di.scope.ActivityScope
import com.example.ferquies.todoapp.view.tasks.adapter.ItemTouchHelperCallback
import com.example.ferquies.todoapp.view.tasks.adapter.TaskListAdapter
import dagger.Module
import dagger.Provides

/**
 * Created by Fernando Q. Esquitino
 * Email: ferquies@gmail.com
 * Twitter: @ferquies
 * 1/25/18
 */
@Module
class TasksFragmentModule {

    @Provides
    @ActivityScope
    fun provideTodoListAdapter(tasksFragment: TasksFragment): TaskListAdapter = TaskListAdapter(
            tasksFragment)

    @Provides
    @ActivityScope
    fun providesItemTouchHelperCallback(adapter: TaskListAdapter,
            context: Context): ItemTouchHelperCallback {
        return ItemTouchHelperCallback(adapter, context)
    }

    @Provides
    @ActivityScope
    fun provideItemTouchHelper(callback: ItemTouchHelperCallback): ItemTouchHelper {
        return ItemTouchHelper(callback)
    }
}