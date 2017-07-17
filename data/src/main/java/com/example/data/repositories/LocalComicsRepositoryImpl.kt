package com.example.data.repositories

import com.example.bos.Comic
import com.example.data.datasources.ComicsDao
import com.example.data.datasources.dtos.mappers.ComicEntityMapper
import com.example.repositories.LocalComicsRepository
import javax.inject.Inject

class LocalComicsRepositoryImpl
@Inject constructor(val comicsDao: ComicsDao,
                    val comicEntityMapper: ComicEntityMapper):
        LocalComicsRepository {

    override fun saveComics(comics: List<Comic>) {
        val comicEntities = comicEntityMapper.toDto(comics)
        comicsDao.insert(comicEntities)
    }

    override fun getComics(): List<Comic> {
        val comics = comicsDao.loadAllComics()
        return comicEntityMapper.toBusinessObject(comics)
    }

}