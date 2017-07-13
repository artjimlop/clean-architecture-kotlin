package com.example.usecases

import com.example.callback.ComicsCallback
import com.example.executor.PostExecutionThread
import com.example.executor.ThreadExecutor
import com.example.model.Comic
import java.util.*
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
        val comic = Comic(0, "el perreo de la muerte", "yung beef skinny nigga",
                1, "http://los40es00.epimg.net/los40/imagenes/2017/03/15/actualidad/1489568285_914454_1489569490_noticia_normal.jpg",
                Collections.emptyList())
        notifyLoaded(mutableListOf(comic))
        notifyError(RuntimeException("fake comic"))
    }

    private fun notifyLoaded(comicsCollection: Collection<Comic>) {
        this.postExecutionThread.post(Runnable { callback?.onComicsLoaded(comicsCollection) })
    }

    private fun notifyError(runtimeException: RuntimeException) {
        this.postExecutionThread.post(Runnable { callback?.onError(runtimeException) })
    }
}
