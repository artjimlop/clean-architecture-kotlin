package com.example.data.repositories

import com.example.bos.Comic
import com.example.data.datasources.ComicsDao
import com.example.data.datasources.ImagesDao
import com.example.data.datasources.dtos.entities.ComicEntity
import com.example.data.datasources.dtos.mappers.ComicEntityMapper
import com.example.data.datasources.dtos.mappers.ImageEntityMapper
import com.example.repositories.LocalComicsRepository
import javax.inject.Inject

class LocalComicsRepositoryImpl
@Inject constructor(val comicsDao: ComicsDao,
                    val imagesDao: ImagesDao,
                    val comicEntityMapper: ComicEntityMapper,
                    val imageEntityMapper: ImageEntityMapper):
        LocalComicsRepository {

    override fun saveComics(comics: List<Comic>) {
        val comicEntities = comicEntityMapper.toDto(comics)
        comicsDao.insert(comicEntities)
        comics.map { comic -> saveImages(comic) }
    }

    private fun saveImages(comic: Comic) {
        imagesDao.insert(imageEntityMapper.extractImages(comic))
    }

    override fun getComics(): List<Comic> {
        val comics = comicsDao.loadAllComics()
        return comics.map { comic -> mapComic(comic) }
    }

    private fun mapComic(comic: ComicEntity) = comicEntityMapper.toBusinessObject(comic, getImagesByComic(comic))

    private fun getImagesByComic(comic: ComicEntity) = imagesDao.loadImagesById(comic.id)

}