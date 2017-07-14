package com.example.arturo.mycomics.infrastructure.activities

import com.example.arturo.mycomics.ui.comics.views.ComicsActivity
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun inject(comicsActivity: ComicsActivity)

}