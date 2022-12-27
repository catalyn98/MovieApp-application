package com.mobiversal.movieapplication.genre

import com.mobiversal.movieapplication.network.Constants.API_KEY
import com.mobiversal.movieapplication.network.Constants.LANGUAGE
import com.mobiversal.movieapplication.network.executeAndDeliver
import retrofit2.Retrofit
import java.lang.Exception

class GenreRemoteDataSource(retrofit: Retrofit) {

    private val apiService : GenreAPIService = retrofit.create(GenreAPIService::class.java)
    private val genreMapper  = GenreMapper()

    @Throws(Exception::class)
    fun getGenres(): List<Genre>{
        return apiService.getGenres(API_KEY, LANGUAGE)
            .executeAndDeliver()
            .genres
            .map { genreMapper.map(it) }
    }
}