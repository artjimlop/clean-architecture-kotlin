package com.example.arturo.mycomics.ui.comics.models.mapper

import com.example.arturo.mycomics.ui.comics.models.ComicModel
import com.example.model.Comic
import javax.inject.Inject

class ComicModelMapper @Inject constructor() {

    fun toModel(bo: Comic?): ComicModel {
        return ComicModel(bo!!.id,
                bo.title,
                bo.description,
                bo.pageCount,
                bo.thumbnailUrl,
                bo.imageUrls)
    }

    fun toModel(bos: MutableList<Comic>?): List<ComicModel> {
        return bos!!.map { comic -> toModel(comic) }
    }


}
