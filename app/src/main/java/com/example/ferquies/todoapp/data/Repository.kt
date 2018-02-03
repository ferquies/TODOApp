package com.example.ferquies.todoapp.data

import android.arch.lifecycle.LiveData
import com.example.ferquies.todoapp.data.database.todo.TodoDao
import com.example.ferquies.todoapp.domain.database.Todo
import java.util.concurrent.Executor
import javax.inject.Inject
import javax.inject.Singleton

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

    fun deleteTask(task: Todo) = executor.execute { todoDao.delete(task) }

    fun changeOrder(tasks: List<Todo>) {
        executor.execute {
            for (task in tasks) {
                updateTask(task)
            }
        }
    }
}