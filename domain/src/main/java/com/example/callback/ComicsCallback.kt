package com.example.callback

import com.example.bos.Comic

interface ComicsCallback {
    fun onComicsLoaded(comicCollection: Collection<Comic>)

    fun onError(exception: RuntimeException)
}
