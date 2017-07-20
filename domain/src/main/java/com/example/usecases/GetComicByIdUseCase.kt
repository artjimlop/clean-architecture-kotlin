package com.example.usecases

import com.example.bos.Comic
import com.example.callback.ComicCallback
import com.example.executor.PostExecutionThread
import com.example.executor.ThreadExecutor
import com.example.repositories.LocalComicsRepository

class GetComicByIdUseCase @Inject constructor(val threadExecutor: ThreadExecutor,
                                           val postExecutionThread: PostExecutionThread,
                                           val localComicsRepository: LocalComicsRepository): UseCase {

    private var comicId: Int? = null
    private var callback: ComicCallback? = null

    fun execute(comicId: Int, callback: ComicCallback) {
        this.comicId = comicId
        this.callback = callback
        threadExecutor.execute(this)
    }

    override fun run() {
        notifyLoaded(localComicsRepository.getComic(comicId))
    }

    private fun notifyLoaded(comic: Comic) {
        this.postExecutionThread.post(Runnable { callback?.onComicLoaded(comic) })
    }
}
