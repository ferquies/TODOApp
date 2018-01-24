package com.example.ferquies.todoapp.domain.detail

import com.example.ferquies.todoapp.domain.database.Todo

/**
 * Created by Fernando Q. Esquitino
 * Email: ferquies@gmail.com
 * Twitter: @ferquies
 * 1/22/18
 */
data class DetailViewState(val isEditing: Boolean = false, val todo: Todo = Todo())