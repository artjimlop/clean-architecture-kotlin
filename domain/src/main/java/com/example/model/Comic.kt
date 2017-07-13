package com.example.model

data class Comic(val id: Int,
                      val title: String,
                      val description: String,
                      val pageCount: Int,
                      val thumbnailUrl: String,
                      val imageUrls: MutableList<String>)
