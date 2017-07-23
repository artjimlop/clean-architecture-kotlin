package com.example.data.datasources.dtos.mappers

import com.example.bos.Comic
import com.example.data.datasources.dtos.entities.ComicEntity
import com.example.data.datasources.dtos.entities.ImageEntity
import javax.inject.Inject

class ComicEntityMapper @Inject constructor() {

    fun toDto(bo: Comic?): ComicEntity {
        return ComicEntity(bo!!.id,
                bo.title,
                bo.description,
                bo.pageCount,
                bo.thumbnailUrl)
    }

    fun toBusinessObject(entity: ComicEntity, images: List<ImageEntity>): Comic {
        val urls = images.map { (url) -> url }
        return Comic(entity.id,
                entity.title,
                entity.description,
                entity.pageCount,
                entity.thumbnailUrl,
                urls as MutableList<String>)
    }
}
