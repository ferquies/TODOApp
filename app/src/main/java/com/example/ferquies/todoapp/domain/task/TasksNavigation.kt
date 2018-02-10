package com.example.ferquies.todoapp.domain.task

/**
 * Created by Fernando Q. Esquitino
 * Email: ferquies@gmail.com
 * Twitter: @ferquies
 * 1/21/18
 */
sealed class TasksNavigation {
    class Detail constructor(val taskId: Int) : TasksNavigation()
}