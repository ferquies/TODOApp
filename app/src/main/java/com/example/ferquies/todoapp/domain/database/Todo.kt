package com.example.ferquies.todoapp.domain.database

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Fernando Q. Esquitino
 * Email: ferquies@gmail.com
 * Twitter: @ferquies
 * 1/21/18
 */
@Entity
data class Todo(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        val title: String,
        val detail: String) {
    @Ignore
    constructor() : this(title = "", detail = "")
}
