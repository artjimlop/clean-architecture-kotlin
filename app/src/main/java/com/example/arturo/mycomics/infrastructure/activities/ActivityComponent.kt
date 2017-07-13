package com.example.arturo.mycomics.infrastructure.activities

import com.example.arturo.mycomics.ui.BaseActivity
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun inject(baseActivity: BaseActivity)

}