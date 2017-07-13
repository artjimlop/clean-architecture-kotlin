package com.example.arturo.mycomics.ui.comics.presenters

import com.example.arturo.mycomics.ui.comics.models.mapper.ComicModelMapper
import com.example.arturo.mycomics.ui.comics.views.ComicsView
import com.example.callback.ComicsCallback
import com.example.model.Comic
import com.example.usecases.GetComicsUseCase
import javax.inject.Inject

class ComicsPresenter @Inject constructor(val getComicsUseCase: GetComicsUseCase,
                                          val comicModelMapper: ComicModelMapper) {

    private var view: ComicsView? = null

    fun initialize(comicsActivity: ComicsView, characterId: Int) {
        this.view = comicsActivity
        loadComics(characterId)
    }

    fun loadComics(characterId: Int) {
        getComicsUseCase.execute(characterId, object : ComicsCallback {
            override fun onError(exception: RuntimeException) {
                showError(exception.message)
            }

            override fun onComicsLoaded(comicCollection: Collection<Comic>) {
            }

        })
    }

    private fun showError(message: String?) {
        view?.showError(message!!)
    }


}