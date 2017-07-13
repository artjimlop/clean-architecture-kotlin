package com.example.arturo.mycomics.infrastructure

import com.example.arturo.mycomics.MyComicsApplication
import com.example.arturo.mycomics.infrastructure.threading.UIThread
import com.example.arturo.mycomics.ui.navigation.Navigator
import com.example.executor.JobExecutor
import com.example.executor.PostExecutionThread
import com.example.executor.ThreadExecutor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: MyComicsApplication) {
    @Provides @Singleton fun app() = app

    fun navigator(): Navigator = Navigator()

    @Provides
    @Singleton
    fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @Singleton
    fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread {
        return uiThread
    }

}