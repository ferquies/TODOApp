package com.example.ferquies.todoapp.view.tasks

import com.example.ferquies.todoapp.di.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Fernando Q. Esquitino
 * Email: ferquies@gmail.com
 * Twitter: @ferquies
 * 1/20/18
 */
@Module
abstract class TasksFragmentProvider {
    @ContributesAndroidInjector(modules = [TasksFragmentModule::class])
    @ActivityScope
    abstract fun provideHomeFragment(): TasksFragment
}