package com.example.arturo.mycomics.infrastructure.extensions

import android.app.Activity
import com.example.arturo.mycomics.MyComicsApplication

val Activity.app: MyComicsApplication
    get() = application as MyComicsApplication
