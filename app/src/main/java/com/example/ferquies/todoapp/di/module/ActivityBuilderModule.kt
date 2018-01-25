package com.example.ferquies.todoapp.di.module

import com.example.ferquies.todoapp.view.home.HomeActivity
import com.example.ferquies.todoapp.view.home.HomeFragmentProvider
import com.example.ferquies.todoapp.view.detail.DetailActivity
import com.example.ferquies.todoapp.view.detail.DetailFragmentProvider
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

    @ContributesAndroidInjector(modules = [HomeFragmentProvider::class])
    internal abstract fun homeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = [DetailFragmentProvider::class])
    internal abstract fun detailActivity(): DetailActivity

}