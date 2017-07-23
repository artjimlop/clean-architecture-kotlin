package com.example.repositories

import com.example.bos.Comic

interface LocalComicsRepository {

    fun saveComics(comics: List<Comic>)

    fun getComic(comicId: Int?): Comic
}