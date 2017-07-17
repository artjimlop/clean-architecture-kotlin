package com.example.data.infrastructure

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.data.datasources.ComicsDao
import com.example.data.datasources.dtos.entities.ComicEntity

@Database(entities = arrayOf(ComicEntity::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun comicsDao(): ComicsDao
}