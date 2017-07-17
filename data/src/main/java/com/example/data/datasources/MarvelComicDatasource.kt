package com.example.data.datasources

import com.example.data.datasources.dtos.responses.ComicsResponse


interface  MarvelComicDatasource {

    fun getComics(characterId: Int): ComicsResponse

}