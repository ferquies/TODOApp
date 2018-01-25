package com.example.ferquies.todoapp.view.home

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
    fun provideTodoListAdapter(homeFragment: HomeFragment): TodoListAdapter = TodoListAdapter(
            homeFragment)

}