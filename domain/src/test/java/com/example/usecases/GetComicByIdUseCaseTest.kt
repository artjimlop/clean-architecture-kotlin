package com.example.usecases

import com.example.bos.Comic
import com.example.callback.ComicCallback
import com.example.executor.TestExecutionThread
import com.example.executor.TestThreadExecutor
import com.example.repositories.LocalComicsRepository
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.util.*

class GetComicByIdUseCaseTest {

    private val FAKE_COMIC_ID = 0

    lateinit var localComicsRepository: LocalComicsRepository
    lateinit var comicCallback: ComicCallback

    private lateinit var useCase: GetComicByIdUseCase

    @Before
    fun setUp() {
        localComicsRepository = Mockito.mock(LocalComicsRepository::class.java)
        comicCallback = Mockito.mock(ComicCallback::class.java)
        val postExecutionThread = TestExecutionThread()
        val threadExecutor = TestThreadExecutor()
        useCase = GetComicByIdUseCase(threadExecutor, postExecutionThread,
                localComicsRepository)
    }

    @Test
    fun comicObtainedFromLocalRepository() {
        useCase.execute(FAKE_COMIC_ID, comicCallback)

        verify(localComicsRepository).getComic(FAKE_COMIC_ID)
    }

    @Test
    fun comicObtainedFromLocalRepositoryIsNotified() {
        Mockito.`when`(localComicsRepository.getComic(FAKE_COMIC_ID)).thenReturn(comic())

        useCase.execute(FAKE_COMIC_ID, comicCallback)

        verify(comicCallback).onComicLoaded(any())
    }

    private fun comic(): Comic? {
        return Comic(FAKE_COMIC_ID, "", "", 0,
                "", Collections.emptyList())

    }
}