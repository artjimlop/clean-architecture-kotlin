package com.example.arturo.mycomics.infrastructure.activities

import android.content.Context
import com.example.arturo.mycomics.ui.comics.views.ComicsActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(val activity: ComicsActivity) {

    @Provides
    fun context(): Context = activity
}
