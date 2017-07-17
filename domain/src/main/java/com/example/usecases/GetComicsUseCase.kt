package com.example.usecases

import com.example.bos.Comic
import com.example.callback.ComicsCallback
import com.example.executor.PostExecutionThread
import com.example.executor.ThreadExecutor
import com.example.repositories.LocalComicsRepository
import com.example.repositories.MarvelRepository
import javax.inject.Inject

class GetComicsUseCase @Inject constructor(val threadExecutor: ThreadExecutor,
                                           val postExecutionThread: PostExecutionThread,
                                           val marvelRepository: MarvelRepository,
                                           val localComicsRepository: LocalComicsRepository): UseCase {

    private var characterId: Int? = null
    private var callback: ComicsCallback? = null

    fun execute(characterId: Int, callback: ComicsCallback) {
        this.characterId = characterId
        this.callback = callback
        threadExecutor.execute(this)
    }

    override fun run() {
        val localComics = localComicsRepository.getComics()
        notifyLoaded(localComics)
        val remoteComics = marvelRepository.getComics(characterId!!)
        localComicsRepository.saveComics(remoteComics)
        notifyLoaded(remoteComics)
    }

    private fun notifyLoaded(comicsCollection: Collection<Comic>) {
        this.postExecutionThread.post(Runnable { callback?.onComicsLoaded(comicsCollection) })
    }

    private fun notifyError(runtimeException: RuntimeException) {
        this.postExecutionThread.post(Runnable { callback?.onError(runtimeException) })
    }
}
