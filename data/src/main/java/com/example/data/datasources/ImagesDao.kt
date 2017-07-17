package com.example.data.datasources

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.data.datasources.dtos.entities.ImageEntity

@Dao
interface ImagesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(images: List<ImageEntity>)

    @Query("SELECT * FROM images WHERE comicId= (:id)")
    fun loadImagesById(id: Int): List<ImageEntity>
}

