package com.example.arturo.mycomics.ui

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.example.arturo.mycomics.ui.comics.presenters.ComicsPresenter
import com.example.arturo.mycomics.ui.navigation.Navigator
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var navigator: Navigator
    @Inject
    lateinit var presenter: ComicsPresenter

    internal var toolbar: Toolbar? = null

    fun setUpToolbar(toolbar: Toolbar?, showUpButton: Boolean) {
        this.toolbar = toolbar
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(showUpButton)
            supportActionBar!!.setDisplayShowTitleEnabled(true)
        }
    }
}
