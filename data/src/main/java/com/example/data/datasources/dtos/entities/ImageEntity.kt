package com.example.data.datasources.dtos.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "images")
data class ImageEntity (@ColumnInfo(name = "url") val url: String,
                        @ColumnInfo(name = "comicId") val id: Int) {
    @ColumnInfo(name = "imageId")
    @PrimaryKey(autoGenerate = true) var imageId: Long = 0
}