package com.example.repositories

import com.example.bos.Comic

interface LocalComicsRepository {

    fun getComics(): List<Comic>

    fun saveComics(comics: List<Comic>)
}