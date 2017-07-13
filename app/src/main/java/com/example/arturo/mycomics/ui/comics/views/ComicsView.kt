package com.example.arturo.mycomics.ui.comics.views

import com.example.arturo.mycomics.ui.comics.models.ComicModel

interface ComicsView {

    fun showComics(models: MutableList<ComicModel>)

    fun showError(error: String)
}
