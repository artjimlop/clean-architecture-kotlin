package com.example.arturo.mycomics.infrastructure

import com.example.arturo.mycomics.MyComicsApplication
import com.example.arturo.mycomics.infrastructure.activities.ActivityComponent
import com.example.arturo.mycomics.infrastructure.activities.ActivityModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(app: MyComicsApplication)

    fun plus(activityModule: ActivityModule): ActivityComponent
}
