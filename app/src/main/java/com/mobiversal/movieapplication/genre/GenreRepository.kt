package com.mobiversal.movieapplication.genre

import com.mobiversal.movieapplication.dataBase.DataBase
import com.mobiversal.movieapplication.network.APIClient

class GenreRepository {

    companion object{
        val instance = GenreRepository()
    }

    private val genreLocalDataSource = GenreLocalDataSource(
        DataBase.instance
    )

    private val genreRemoteDataSource = GenreRemoteDataSource(
        retrofit = APIClient.instance.retrofit
    )

    suspend fun getAll() = genreLocalDataSource.getAll()
    suspend fun save(genre: Genre) = genreLocalDataSource.save(genre)
    suspend fun saveAll(genres: List<Genre>) = genreLocalDataSource.saveAll(genres)
    suspend fun delete(genre: Genre) = genreLocalDataSource.delete(genre)
    suspend fun deleteAll() = genreLocalDataSource.deleteAll()
    suspend fun deleteAll(favoriteActors: List<Genre>) = genreLocalDataSource.deleteAll(favoriteActors)
    suspend fun replaceAll(genres: List<Genre>) = genreLocalDataSource.replaceAll(genres)
    @Throws(Exception::class)
    suspend fun getAllRemote() = genreRemoteDataSource.getGenres()
}