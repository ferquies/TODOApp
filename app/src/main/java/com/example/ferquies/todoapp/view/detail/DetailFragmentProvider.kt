package com.example.ferquies.todoapp.view.detail

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Fernando Q. Esquitino
 * Email: ferquies@gmail.com
 * Twitter: @ferquies
 * 1/21/18
 */
@Module
abstract class DetailFragmentProvider {
    @ContributesAndroidInjector
    abstract fun provideDetailFragment(): DetailFragment
}