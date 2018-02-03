package com.example.ferquies.todoapp.data.database.todo

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.example.ferquies.todoapp.base.BaseDao
import com.example.ferquies.todoapp.domain.database.Todo

/**
 * Created by Fernando Q. Esquitino
 * Email: ferquies@gmail.com
 * Twitter: @ferquies
 * 1/21/18
 */
@Dao
abstract class TodoDao : BaseDao<Todo> {
    @Query("SELECT * FROM Todo WHERE status = :status ORDER BY sequence")
    abstract fun getTasks(status: Int): LiveData<List<Todo>>

    @Query("SELECT * FROM Todo WHERE id = :id")
    abstract fun getTask(id: Int): LiveData<Todo>

    @Query("SELECT * FROM Todo WHERE id = :id")
    abstract fun getTaskNoLive(id: Int): Todo

    @Query("SELECT * FROM Todo WHERE status = :status AND sequence BETWEEN :oldPosition AND :newPosition")
    abstract fun getTasksToOrder(status: Int, oldPosition: Int, newPosition: Int): List<Todo>
}