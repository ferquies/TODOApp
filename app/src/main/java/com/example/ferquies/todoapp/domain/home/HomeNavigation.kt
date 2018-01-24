package com.example.ferquies.todoapp.domain.home

/**
 * Created by Fernando Q. Esquitino
 * Email: ferquies@gmail.com
 * Twitter: @ferquies
 * 1/21/18
 */
sealed class HomeNavigation {
    class AddTodo : HomeNavigation()

    class Detail constructor(val todoId: Int) : HomeNavigation()
}