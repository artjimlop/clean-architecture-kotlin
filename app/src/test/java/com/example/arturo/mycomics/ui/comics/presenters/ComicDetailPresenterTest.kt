package com.example.arturo.mycomics.ui.comics.presenters

import com.example.arturo.mycomics.ui.comics.models.mapper.ComicModelMapper
import com.example.arturo.mycomics.ui.comics.views.ComicDetailView
import com.example.bos.Comic
import com.example.callback.ComicCallback
import com.example.usecases.GetComicByIdUseCase
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doAnswer
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.*

class ComicDetailPresenterTest {

    private val FAKE_COMIC_ID = 0

    @Mock lateinit var getComicByIdUseCase: GetComicByIdUseCase
    @Mock lateinit var comicDetailView: ComicDetailView
    private lateinit var presenter: ComicDetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = ComicDetailPresenter(getComicByIdUseCase, ComicModelMapper())
    }

    @Test
    fun showComicWhenReceivedFromUseCase() {
        setupComicCallback()

        presenter.initialize(comicDetailView, FAKE_COMIC_ID)

        verify(comicDetailView).showComic(any())
    }

    private fun setupComicCallback() {
        doAnswer {
            val callback = it.arguments[1] as ComicCallback
            callback.onComicLoaded(comic())
            null
        }.`when`(getComicByIdUseCase).execute(ArgumentMatchers.anyInt(), any())
    }

    private fun comic(): Comic {
        return Comic(0, "title", "description",
                0, "thumbnail_url", Collections.emptyList())
    }
}