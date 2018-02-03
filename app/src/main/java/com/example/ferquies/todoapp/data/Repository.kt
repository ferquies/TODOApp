package com.example.ferquies.todoapp.data

import android.arch.lifecycle.LiveData
import com.example.ferquies.todoapp.data.database.todo.TodoDao
import com.example.ferquies.todoapp.domain.database.Todo
import java.util.concurrent.Executor
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.max
import kotlin.math.min

/**
 * Created by Fernando Q. Esquitino
 * Email: ferquies@gmail.com
 * Twitter: @ferquies
 * 1/21/18
 */
@Singleton
class Repository @Inject constructor(private val todoDao: TodoDao,
        private val executor: Executor) {

    fun getTasks(status: Int): LiveData<List<Todo>> = todoDao.getTasks(status)

    fun getTask(id: Int): LiveData<Todo> = todoDao.getTask(id)

    fun addTask(task: Todo) = executor.execute { todoDao.insert(task) }

    fun updateTask(task: Todo) = executor.execute { todoDao.update(task) }

    fun deleteTask(task: Todo) {
        executor.execute {
            todoDao.delete(task)
            val tasksToOrder = getTasksToOrder(task.status!!, task.sequence!!, Int.MAX_VALUE)

            for (taskToOrder in tasksToOrder) {
                updateTask(taskToOrder.copy(sequence = taskToOrder.sequence!! - 1))
            }
        }
    }

    private fun getTaskNoLive(id: Int): Todo = todoDao.getTaskNoLive(id)

    private fun getTasksToOrder(status: Int, oldPosition: Int, newPosition: Int): List<Todo> {
        return todoDao.getTasksToOrder(status, oldPosition, newPosition)
    }

    fun changeOrder(task: Todo) {
        executor.execute {
            val oldPosition = getTaskNoLive(task.id).sequence!!
            val newPosition = task.sequence!!
            val tasksToOrder = getTasksToOrder(task.status!!, min(oldPosition, newPosition),
                    max(oldPosition, newPosition) + 1)

            for (taskToOrder in tasksToOrder) {
                if (taskToOrder.id == task.id) {
                    updateTask(task)
                } else {
                    val newSequence = if (oldPosition > newPosition) taskToOrder.sequence!! + 1 else taskToOrder.sequence!! - 1
                    updateTask(taskToOrder.copy(sequence = newSequence))
                }
            }
        }
    }
}