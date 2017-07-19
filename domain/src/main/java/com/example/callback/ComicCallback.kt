package com.example.callback

import com.example.bos.Comic

interface ComicCallback {
    fun onComicLoaded(comic: Comic)

    fun onError(exception: RuntimeException)
}