package com.example.data.datasources.dtos.mappers

import com.example.bos.Comic
import com.example.data.datasources.dtos.responses.ComicResponse
import com.example.data.datasources.dtos.responses.ComicsResponse
import com.example.data.datasources.dtos.responses.ImageResponse
import javax.inject.Inject

class ComicsResponseMapper @Inject constructor() {

    val EXTENSION_SEPARATOR = "."

    fun toBusinessObject(response: ComicResponse?): Comic {
        return Comic(response!!.id,
                response.title,
                extractDescription(response),
                response.images.size,
                imageUrl(response.thumbnail),
                imageUrls(response.images) as MutableList<String>)
    }

    fun toBusinessObject(response: ComicsResponse): List<Comic> {
        return response.data.results.map { comicsResponse -> toBusinessObject(comicsResponse) }
    }

    fun imageUrl(dto: ImageResponse?): String {
        return dto!!.path + EXTENSION_SEPARATOR + dto.extension
    }

    fun imageUrls(dtos: List<ImageResponse>?): List<String> {
        return dtos!!.map { imageResponse ->  imageUrl(imageResponse)}
    }

    private fun extractDescription(response: ComicResponse): String {
        val description =
                response.description ?: ""
        return description
    }
}
