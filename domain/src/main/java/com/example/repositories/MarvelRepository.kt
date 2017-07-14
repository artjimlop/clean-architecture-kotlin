package com.example.repositories

import com.example.bos.Comic

interface MarvelRepository {

    fun getComics(characterId: Int): List<Comic>

}
