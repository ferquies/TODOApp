package com.example.ferquies.todoapp.domain.home

/**
 * Created by Fernando Q. Esquitino
 * Email: ferquies@gmail.com
 * Twitter: @ferquies
 * 1/21/18
 */
sealed class TasksNavigation {
    class AddTask : TasksNavigation()

    class Detail constructor(val taskId: Int) : TasksNavigation()
}