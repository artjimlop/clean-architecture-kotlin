package com.example.data.datasources

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.example.data.datasources.dtos.entities.ComicEntity


@Dao
interface ComicsDao {

    @Insert(onConflict = REPLACE)
    fun insert(comics: List<ComicEntity>)

    @Query("SELECT * FROM comics WHERE id= (:comicId)")
    fun loadComicById(comicId: Int): ComicEntity

    @Query("SELECT * FROM comics")
    fun loadAllComics(): List<ComicEntity>
}