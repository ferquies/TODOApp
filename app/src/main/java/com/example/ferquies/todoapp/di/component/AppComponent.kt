package com.example.ferquies.todoapp.di.component

import android.app.Application
import android.content.Context
import com.example.ferquies.todoapp.App
import com.example.ferquies.todoapp.di.module.ActivityBuilderModule
import com.example.ferquies.todoapp.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import java.util.concurrent.Executor
import javax.inject.Singleton

/**
 * Created by Fernando Q. Esquitino
 * Email: ferquies@gmail.com
 * Twitter: @ferquies
 * 1/20/18
 */
@Singleton
@Component(modules = [AppModule::class, AndroidInjectionModule::class,
        ActivityBuilderModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)

    fun provideContext(): Context
}