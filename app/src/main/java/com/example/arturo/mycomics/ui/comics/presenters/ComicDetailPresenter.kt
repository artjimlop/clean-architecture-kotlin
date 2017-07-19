package com.example.arturo.mycomics.ui.comics.presenters

import com.example.arturo.mycomics.ui.comics.models.ComicModel
import com.example.arturo.mycomics.ui.comics.models.mapper.ComicModelMapper
import com.example.arturo.mycomics.ui.comics.views.ComicDetailView
import com.example.bos.Comic
import com.example.callback.ComicCallback
import com.example.usecases.GetComicByIdUseCase
import javax.inject.Inject

class ComicDetailPresenter @Inject constructor(val getComicByIdUseCase: GetComicByIdUseCase,
                                               val comicModelMapper: ComicModelMapper) {

    private var view: ComicDetailView? = null

    fun initialize(comicDetailView: ComicDetailView, comicId: Int) {
        this.view = comicDetailView
        loadComic(comicId)
    }

    private fun loadComic(comicId: Int) {
        getComicByIdUseCase.execute(comicId, object: ComicCallback {
            override fun onComicLoaded(comic: Comic) {
                showComic(comicModelMapper.toModel(comic))
            }

            override fun onError(exception: RuntimeException) {
                showError(exception.message)
            }

        })
    }

    private fun showError(message: String?) {
        view?.showError(message!!)
    }

    private fun showComic(comic: ComicModel) {
        view?.showComic(comic)
    }
}
