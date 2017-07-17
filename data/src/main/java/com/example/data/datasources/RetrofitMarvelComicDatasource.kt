package com.example.data.datasources

import com.example.data.datasources.dtos.responses.ComicsResponse
import com.example.data.net.ComicApiService
import javax.inject.Inject

class RetrofitMarvelComicDatasource @Inject constructor(val comicApiService: ComicApiService) :
        MarvelComicDatasource {

    override fun getComics(characterId: Int): ComicsResponse {
        /*
        Call<VideoListResponseApiEntity> call = videoApiService.getPopularVideos();
      Response<VideoListResponseApiEntity> response = call.execute();
      return response.body().getItems();
         */
        val comicsCall = comicApiService.getComicsByCharacterId(characterId)
        val comicsResponse = comicsCall.execute()
        return comicsResponse.body()!!
    }

}