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

    fun getTodos(): LiveData<List<Todo>> = todoDao.getTodos()

    fun getTodo(id: Int): LiveData<Todo> = todoDao.getTodo(id)

    fun addTodo(todo: Todo) = executor.execute { todoDao.insert(todo) }

    fun updateTodo(todo: Todo) = executor.execute { todoDao.update(todo) }

    fun deleteTodo(todo: Todo) = executor.execute { todoDao.delete(todo) }

}