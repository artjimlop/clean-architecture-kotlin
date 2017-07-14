package com.example.data.dtos

data class ComicResponse (val id: Int,
                          val title:String,
                          val description: String,
                          val thumbnail: ImageResponse,
                          val images: MutableList<ImageResponse>)