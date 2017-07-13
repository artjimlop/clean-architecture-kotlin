package com.example.arturo.mycomics

import android.app.Application
import com.example.arturo.mycomics.infrastructure.AppComponent
import com.example.arturo.mycomics.infrastructure.AppModule
import com.example.arturo.mycomics.infrastructure.DaggerAppComponent

class MyComicsApplication : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}