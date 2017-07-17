package com.example.arturo.mycomics.infrastructure

import android.arch.persistence.room.Room
import com.example.arturo.mycomics.MyComicsApplication
import com.example.arturo.mycomics.R
import com.example.arturo.mycomics.infrastructure.threading.UIThread
import com.example.arturo.mycomics.ui.navigation.Navigator
import com.example.data.datasources.MarvelComicDatasource
import com.example.data.datasources.RetrofitMarvelComicDatasource
import com.example.data.infrastructure.AppDatabase
import com.example.data.net.ApiConstants
import com.example.data.net.ComicApiService
import com.example.data.net.interceptors.AuthInterceptor
import com.example.data.repositories.LocalComicsRepositoryImpl
import com.example.data.repositories.MarvelRepositoryImpl
import com.example.executor.JobExecutor
import com.example.executor.PostExecutionThread
import com.example.executor.ThreadExecutor
import com.example.repositories.LocalComicsRepository
import com.example.repositories.MarvelRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule(val app: MyComicsApplication) {
    @Provides @Singleton fun app() = app

    fun navigator(): Navigator = Navigator()

    @Provides
    @Singleton
    fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @Singleton
    fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread {
        return uiThread
    }

    @Provides
    @Singleton
    fun provideComicsRepository(marvelRepositoryImpl: MarvelRepositoryImpl): MarvelRepository {
        return marvelRepositoryImpl
    }

    @Provides
    @Singleton
    fun provideRetrofitComicDataStore(retrofitMarvelComicDatasource: RetrofitMarvelComicDatasource): MarvelComicDatasource {
        return retrofitMarvelComicDatasource
    }

    @Provides
    @Singleton
    @Named("public_key")
    fun providePublicKey(): String {
        return app.getString(R.string.public_key)
    }

    @Provides
    @Singleton
    @Named("private_key")
    fun providePrivateKey(): String {
        return app.getString(R.string.private_key)
    }

    @Provides
    @Singleton
    @Named("character_id")
    fun provideCharacterId(): Int {
        return app.getString(R.string.character_id).toInt()
    }

    @Provides
    @Singleton
    fun provideComicApiService(authInterceptor: AuthInterceptor): ComicApiService {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).addInterceptor(authInterceptor)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(ApiConstants.ENDPOINT)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create(ComicApiService::class.java)
    }

    @Provides @Singleton
    fun database(app: MyComicsApplication): AppDatabase
            = Room.databaseBuilder(app, AppDatabase::class.java, "comics").build()

    @Provides
    fun comicsDao(appDatabase: AppDatabase) = appDatabase.comicsDao()

    @Provides
    fun imagesDao(appDatabase: AppDatabase) = appDatabase.imagesDao()

    @Provides
    fun provideLocalComicsRepository(localComicsRepositoryImpl: LocalComicsRepositoryImpl): LocalComicsRepository
            = localComicsRepositoryImpl
}