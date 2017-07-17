package com.example.data.datasources.dtos.mappers

import com.example.bos.Comic
import com.example.data.datasources.dtos.entities.ComicEntity
import java.util.*
import javax.inject.Inject

class ComicEntityMapper @Inject constructor() {

    fun toBusinessObject(entity: ComicEntity?): Comic {
        return Comic(entity!!.id,
                entity.title,
                entity.description,
                entity.pageCount,
                entity.thumbnailUrl,
                Collections.emptyList())
    }

    fun toBusinessObject(entities: Collection<ComicEntity>): List<Comic> {
        return entities.map { comicEntity -> toBusinessObject(comicEntity) }
    }

    fun toDto(bo: Comic?): ComicEntity {
        return ComicEntity(bo!!.id,
                bo.title,
                bo.description,
                bo.pageCount,
                bo.thumbnailUrl)
    }

    fun toDto(bos: Collection<Comic>): List<ComicEntity> {
        return bos.map { bo -> toDto(bo) }
    }
}
