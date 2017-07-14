package com.example.data.net

import com.example.data.datasources.dtos.ComicsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ComicApiService {

    @GET("characters/{characterId}/comics") fun getComicsByCharacterId(@Path("characterId") characterId: Int): Call<ComicsResponse>
}