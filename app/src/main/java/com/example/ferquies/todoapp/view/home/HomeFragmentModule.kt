package com.example.ferquies.todoapp.view.home

import android.support.v7.widget.helper.ItemTouchHelper
import com.example.ferquies.todoapp.di.scope.ActivityScope
import com.example.ferquies.todoapp.view.home.adapter.ItemTouchHelperCallback
import com.example.ferquies.todoapp.view.home.adapter.TodoListAdapter
import dagger.Module
import dagger.Provides

/**
 * Created by Fernando Q. Esquitino
 * Email: ferquies@gmail.com
 * Twitter: @ferquies
 * 1/25/18
 */
@Module
class HomeFragmentModule {

    @Provides
    @ActivityScope
    fun provideTodoListAdapter(homeFragment: HomeFragment): TodoListAdapter = TodoListAdapter(
            homeFragment)

    @Provides
    @ActivityScope
    fun providesItemTouchHelperCallback(adapter: TodoListAdapter): ItemTouchHelperCallback {
        return ItemTouchHelperCallback(adapter)
    }

    @Provides
    @ActivityScope
    fun provideItemTouchHelper(callback: ItemTouchHelperCallback): ItemTouchHelper {
        return ItemTouchHelper(callback)
    }
}