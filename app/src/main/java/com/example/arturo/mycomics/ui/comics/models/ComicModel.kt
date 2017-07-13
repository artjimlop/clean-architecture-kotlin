package com.example.arturo.mycomics.ui.comics.models

data class ComicModel(val id: Int,
                      val title: String,
                      val description: String,
                      val pageCount: Int,
                      val thumbnailUrl: String,
                      val imageUrls: MutableList<String>)
