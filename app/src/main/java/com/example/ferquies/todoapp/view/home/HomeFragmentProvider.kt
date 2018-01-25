package com.example.ferquies.todoapp.view.home

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Fernando Q. Esquitino
 * Email: ferquies@gmail.com
 * Twitter: @ferquies
 * 1/20/18
 */
@Module
abstract class HomeFragmentProvider {
    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    abstract fun provideHomeFragment(): HomeFragment
}