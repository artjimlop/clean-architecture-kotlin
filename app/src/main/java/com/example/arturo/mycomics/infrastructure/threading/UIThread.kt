package com.example.arturo.mycomics.infrastructure.threading

import android.os.Handler
import android.os.Looper
import com.example.executor.PostExecutionThread
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UIThread @Inject
constructor() : PostExecutionThread {

    private val handler: Handler = Handler(Looper.getMainLooper())

    override fun post(runnable: Runnable) {
        handler.post(runnable)
    }
}
