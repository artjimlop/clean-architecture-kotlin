package com.example.data.infrastructure

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.data.datasources.ComicsDao
import com.example.data.datasources.ImagesDao
import com.example.data.datasources.dtos.entities.ComicEntity
import com.example.data.datasources.dtos.entities.ImageEntity

@Database(entities = arrayOf(ComicEntity::class, ImageEntity::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun comicsDao(): ComicsDao

    abstract fun imagesDao(): ImagesDao
}