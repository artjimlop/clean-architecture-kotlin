package com.example.data.datasources

import com.example.data.datasources.dtos.entities.ComicEntity
import com.example.data.datasources.dtos.entities.ImageEntity
import javax.inject.Inject

class RoomComicDatasource @Inject constructor(val comicsDao: ComicsDao,
                                              val imagesDao: ImagesDao): LocalComicDatasource {
    override fun loadImagesByComic(comicId: Int): List<ImageEntity> {
        return imagesDao.loadImagesById(comicId)
    }

    override fun saveComics(comicPairs: List<Pair<ComicEntity, List<ImageEntity>>>) {
        val comicEntities = comicPairs.map { (first) -> first }
        comicsDao.insert(comicEntities)
        comicPairs.map { (_, second) ->
            imagesDao.insert(second)}
    }

    override fun getComics(): List<ComicEntity> {
        return comicsDao.loadAllComics()
    }

    override fun loadComicById(comicId: Int): Pair<ComicEntity, List<ImageEntity>> {
        val comic = comicsDao.loadComicById(comicId!!)
        return Pair(comic, getImagesByComic(comic))
    }

    private fun getImagesByComic(comic: ComicEntity) = imagesDao.loadImagesById(comic.id)
}