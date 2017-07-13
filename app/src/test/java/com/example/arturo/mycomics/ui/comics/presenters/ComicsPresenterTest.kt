package com.example.arturo.mycomics.ui.comics.presenters

import com.example.arturo.mycomics.ui.comics.models.mapper.ComicModelMapper
import com.example.arturo.mycomics.ui.comics.views.ComicsView
import com.example.callback.ComicsCallback
import com.example.usecases.GetComicsUseCase
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doAnswer
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.*


class ComicsPresenterTest {

    private val characterId = 0

    @Mock lateinit var getComicsUseCase: GetComicsUseCase
    @Mock lateinit var comicsView: ComicsView
    private lateinit var presenter: ComicsPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = ComicsPresenter(getComicsUseCase, ComicModelMapper())
    }

    @Test
    fun showComicsWhenComicsReceivedFromUseCase() {
        setupComicsCallback()

        presenter.initialize(comicsView, characterId)

        verify(comicsView).showComics(any())
    }

    @Test
    fun shouldNotShowErrorWhenComicsReceivedFromUseCase() {
        setupComicsCallback()

        presenter.initialize(comicsView, characterId)

        verify(comicsView, never()).showError(any())
    }

    @Test
    fun showErrorWhenExceptionRaisedInUseCase() {
        setupComicsErrorCallback()

        presenter.initialize(comicsView, characterId)

        verify(comicsView).showError(any())
    }

    @Test
    fun shouldNotShowComicsWhenExceptionRaisedInUseCase() {
        setupComicsErrorCallback()

        presenter.initialize(comicsView, characterId)

        verify(comicsView, never()).showComics(any())
    }

    private fun setupComicsCallback() {
        doAnswer {
            val callback = it.arguments[1] as ComicsCallback
            callback.onComicsLoaded(Collections.emptyList())
            null
        }.`when`(getComicsUseCase).execute(ArgumentMatchers.anyInt(), any())
    }

    private fun setupComicsErrorCallback() {
        doAnswer {
            val callback = it.arguments[1] as ComicsCallback
            callback.onError(RuntimeException("fake"))
            null
        }.`when`(getComicsUseCase).execute(ArgumentMatchers.anyInt(), any())
    }
}