package com.example.data.datasources.dtos.mappers

import com.example.bos.Comic
import com.example.data.datasources.dtos.entities.ImageEntity
import javax.inject.Inject

class ImageEntityMapper @Inject constructor() {

    fun extractImages(bo: Comic): List<ImageEntity> {
        val imageUrls = bo.imageUrls
        return imageUrls.map { imageUrl -> ImageEntity(imageUrl, bo.id) }
    }
}