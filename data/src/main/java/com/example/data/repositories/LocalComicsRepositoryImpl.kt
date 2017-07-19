package com.example.data.repositories

import com.example.bos.Comic
import com.example.data.datasources.LocalComicDatasource
import com.example.data.datasources.dtos.entities.ComicEntity
import com.example.data.datasources.dtos.entities.ImageEntity
import com.example.data.datasources.dtos.mappers.ComicEntityMapper
import com.example.data.datasources.dtos.mappers.ImageEntityMapper
import com.example.repositories.LocalComicsRepository
import javax.inject.Inject

class LocalComicsRepositoryImpl
@Inject constructor(val localComicDatasource: LocalComicDatasource,
                    val comicEntityMapper: ComicEntityMapper,
                    val imageEntityMapper: ImageEntityMapper):
        LocalComicsRepository {

    override fun getComic(comicId: Int?): Comic {
        val comicPair = localComicDatasource.loadComicById(comicId!!)
        return comicEntityMapper.toBusinessObject(comicPair.first, comicPair.second)
    }

    override fun saveComics(comics: List<Comic>) {
        localComicDatasource.saveComics(comics.map { comic -> pairComic(comic) })
    }

    private fun pairComic(comic: Comic): Pair<ComicEntity, List<ImageEntity>> {
        return Pair(comicEntityMapper.toDto(comic), imageEntityMapper.extractImages(comic))
    }

    override fun getComics(): List<Comic> {
        val comics = localComicDatasource.getComics()
        return comics.map { comic -> mapComic(comic) }
    }

    private fun mapComic(comic: ComicEntity) = comicEntityMapper.toBusinessObject(comic, getImagesByComic(comic))

    private fun getImagesByComic(comic: ComicEntity) = localComicDatasource.loadImagesByComic(comic.id)

}