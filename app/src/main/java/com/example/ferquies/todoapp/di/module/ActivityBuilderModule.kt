package com.example.ferquies.todoapp.di.module

import com.example.ferquies.todoapp.ui.home.HomeActivity
import com.example.ferquies.todoapp.ui.tasks.TasksFragmentProvider
import com.example.ferquies.todoapp.ui.detail.DetailActivity
import com.example.ferquies.todoapp.ui.detail.DetailFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Fernando Q. Esquitino
 * Email: ferquies@gmail.com
 * Twitter: @ferquies
 * 1/20/18
 */
@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [TasksFragmentProvider::class])
    internal abstract fun homeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = [DetailFragmentProvider::class])
    internal abstract fun detailActivity(): DetailActivity

}