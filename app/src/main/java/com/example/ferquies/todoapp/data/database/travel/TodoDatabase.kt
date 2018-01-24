package com.example.ferquies.todoapp.data.database.travel

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.ferquies.todoapp.domain.database.Todo

/**
 * Created by Fernando Q. Esquitino
 * Email: ferquies@gmail.com
 * Twitter: @ferquies
 * 1/21/18
 */
@Database(entities = [Todo::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun travelDao(): TodoDao
}