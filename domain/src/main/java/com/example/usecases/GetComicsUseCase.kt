package com.example.usecases

import com.example.callback.ComicsCallback
import com.example.executor.PostExecutionThread
import com.example.executor.ThreadExecutor
import com.example.model.Comic
import javax.inject.Inject

class GetComicsUseCase @Inject constructor(val threadExecutor: ThreadExecutor,
                                           val postExecutionThread: PostExecutionThread): UseCase {

    private var characterId: Int? = null
    private var callback: ComicsCallback? = null

    fun execute(characterId: Int, callback: ComicsCallback) {
        this.characterId = characterId
        this.callback = callback
        threadExecutor.execute(this)
    }

    override fun run() {
        notifyError(RuntimeException("fake exception"))
    }

    private fun notifyLoaded(comicsCollection: Collection<Comic>) {
        this.postExecutionThread.post(Runnable { callback?.onComicsLoaded(comicsCollection) })
    }

    private fun notifyError(runtimeException: RuntimeException) {
        this.postExecutionThread.post(Runnable { callback?.onError(runtimeException) })
    }
}
