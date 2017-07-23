package com.example.data.datasources

import com.example.data.datasources.dtos.entities.ComicEntity
import com.example.data.datasources.dtos.entities.ImageEntity

interface LocalComicDatasource {
    fun loadComicById(comicId: Int): Pair<ComicEntity, List<ImageEntity>>
    fun saveComics(comicPairs: List<Pair<ComicEntity, List<ImageEntity>>>)
    fun loadImagesByComic(comicId: Int): List<ImageEntity>
}