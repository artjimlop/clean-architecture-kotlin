package com.example.callback

import com.example.model.Comic

interface ComicsCallback {
    fun onComicsLoaded(comicCollection: Collection<Comic>)

    fun onError(exception: RuntimeException)
}
