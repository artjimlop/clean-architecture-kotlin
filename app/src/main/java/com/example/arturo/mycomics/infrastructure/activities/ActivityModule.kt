package com.example.arturo.mycomics.infrastructure.activities

import android.content.Context
import com.example.arturo.mycomics.ui.BaseActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(val activity: BaseActivity) {

    @Provides
    fun context(): Context = activity
}
