package com.example.ferquies.todoapp.di.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.example.ferquies.todoapp.data.database.travel.TodoDao
import com.example.ferquies.todoapp.data.database.travel.TodoDatabase
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

/**
 * Created by Fernando Q. Esquitino
 * Email: ferquies@gmail.com
 * Twitter: @ferquies
 * 1/20/18
 */
@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideContext(app: Application): Context = app

    @Singleton
    @Provides
    fun provideTodoDatabase(app: Application): TodoDatabase = Room.databaseBuilder(app,
            TodoDatabase::class.java, "todo.db").build()

    @Singleton
    @Provides
    fun provideTravelDao(todoDatabase: TodoDatabase): TodoDao = todoDatabase.travelDao()

    @Singleton
    @Provides
    fun provideExecutor(): Executor {
        return Executors.newSingleThreadExecutor()
    }

}