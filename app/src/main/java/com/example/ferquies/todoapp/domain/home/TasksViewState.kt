package com.example.ferquies.todoapp.domain.home

import com.example.ferquies.todoapp.domain.database.Todo

/**
 * Created by Fernando Q. Esquitino
 * Email: ferquies@gmail.com
 * Twitter: @ferquies
 * 1/22/18
 */
data class TasksViewState(val isEmptyList: Boolean = true,
        val tasks: List<Todo> = ArrayList())