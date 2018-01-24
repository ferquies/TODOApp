package com.example.ferquies.todoapp.data.database.travel

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
    @Query("SELECT * FROM Todo")
    abstract fun getTravels(): LiveData<List<Todo>>

    @Query("SELECT * FROM Todo WHERE id = :id")
    abstract fun getTravel(id: Int): LiveData<Todo>
}