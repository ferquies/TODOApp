package com.example.ferquies.todoapp.data.database.migration

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.migration.Migration

/**
 * Created by Fernando Q. Esquitino
 * Email: ferquies@gmail.com
 * Twitter: @ferquies
 * 1/25/18
 */
class Migration_1_2 : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE Todo ADD COLUMN status INTEGER")
    }
}

class Migration_2_3 : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE Todo ADD COLUMN sequence INTEGER")
        database.execSQL("UPDATE Todo SET sequence = ${Int.MAX_VALUE}")
    }
}