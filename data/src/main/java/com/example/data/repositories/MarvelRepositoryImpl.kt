package com.example.data.repositories

import com.example.bos.Comic
import com.example.data.datasources.MarvelComicDatasource
import com.example.data.datasources.dtos.mappers.ComicsResponseMapper
import com.example.repositories.MarvelRepository
import javax.inject.Inject

class MarvelRepositoryImpl
@Inject constructor(val marvelComicDatasource: MarvelComicDatasource,
                    val comicsResponseMapper: ComicsResponseMapper):
        MarvelRepository {

    override fun getComics(characterId: Int): List<Comic> {
        val comics = marvelComicDatasource.getComics(characterId)
        return comicsResponseMapper.toBusinessObject(comics)
    }

}
