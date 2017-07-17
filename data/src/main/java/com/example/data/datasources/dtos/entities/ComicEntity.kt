package com.example.data.datasources.dtos.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "comics")
class ComicEntity(@ColumnInfo(name = "id") val id: Int,
                  @ColumnInfo(name = "title") val title: String,
                  @ColumnInfo(name = "description") val description: String,
                  @ColumnInfo(name = "pageCount") val pageCount: Int,
                  @ColumnInfo(name = "thumbnailUrl") val thumbnailUrl: String) {
    @ColumnInfo(name = "comicId")
    @PrimaryKey(autoGenerate = true) var comicId: Long = 0
}
