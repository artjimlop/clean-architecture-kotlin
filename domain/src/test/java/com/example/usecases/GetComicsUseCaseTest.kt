package com.example.usecases

import com.example.bos.Comic
import com.example.callback.ComicsCallback
import com.example.executor.TestExecutionThread
import com.example.executor.TestThreadExecutor
import com.example.repositories.LocalComicsRepository
import com.example.repositories.MarvelRepository
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.atLeastOnce
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.util.*


class GetComicsUseCaseTest {

    private val FAKE_CHARACTER_ID: Int = 0

    lateinit var marvelRepository: MarvelRepository
    lateinit var localComicsRepository: LocalComicsRepository
    lateinit var comicsCallback: ComicsCallback

    private lateinit var useCase: GetComicsUseCase

    @Before
    fun setUp() {
        localComicsRepository = Mockito.mock(LocalComicsRepository::class.java)
        marvelRepository = Mockito.mock(MarvelRepository::class.java)
        comicsCallback = Mockito.mock(ComicsCallback::class.java)
        val postExecutionThread = TestExecutionThread()
        val threadExecutor = TestThreadExecutor()
        useCase = GetComicsUseCase(threadExecutor, postExecutionThread,
                marvelRepository, localComicsRepository)
    }

    @Test
    fun comicsObtainedFromRemoteRepositoryAreSaved() {
        Mockito.`when`(marvelRepository.getComics(FAKE_CHARACTER_ID)).thenReturn(Collections.emptyList<Comic>())

        useCase.execute(FAKE_CHARACTER_ID, comicsCallback)

        verify(localComicsRepository).saveComics(any())
    }

    @Test
    fun comicsObtainedFromRemoteRepositoryAreNotified() {
        Mockito.`when`(marvelRepository.getComics(FAKE_CHARACTER_ID)).thenReturn(Collections.emptyList<Comic>())

        useCase.execute(FAKE_CHARACTER_ID, comicsCallback)

        verify(comicsCallback, atLeastOnce()).onComicsLoaded(any())
    }
}