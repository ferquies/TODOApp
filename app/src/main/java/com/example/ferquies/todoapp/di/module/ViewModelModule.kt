package com.example.ferquies.todoapp.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.ferquies.todoapp.base.ViewModelFactory
import com.example.ferquies.todoapp.di.ViewModelKey
import com.example.ferquies.todoapp.ui.tasks.TasksFragmentViewModel
import com.example.ferquies.todoapp.ui.detail.DetailFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Fernando Q. Esquitino
 * Email: ferquies@gmail.com
 * Twitter: @ferquies
 * 1/20/18
 */
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(TasksFragmentViewModel::class)
    abstract fun bindProfileFragmentViewModel(viewModel: TasksFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailFragmentViewModel::class)
    abstract fun bindTaskDetailFragmentViewModel(
            viewModel: DetailFragmentViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}