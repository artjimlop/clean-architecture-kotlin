package com.example.arturo.mycomics.ui

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

abstract class BaseActivity : AppCompatActivity() {

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
