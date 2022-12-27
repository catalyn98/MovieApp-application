package com.mobiversal.movieapplication.movie

import com.mobiversal.movieapplication.dataBase.DataBase
import com.mobiversal.movieapplication.network.APIClient

class MoviesRepository private constructor() {

    companion object {
        val instance = MoviesRepository()
    }

    private val movieRemoteDataSource = MovieRemoteDataSource(
        retrofit = APIClient.instance.retrofit
    )

    private val movieLocalDataSource = MoviesLocalDataSource(
        DataBase.instance
    )

    fun getAll() = movieLocalDataSource.getAll()
    fun getFavoritesMovies() = movieLocalDataSource.getFavoritesMovies()
    fun getWatchedMovies() = movieLocalDataSource.getWatchedMovies()
    fun save(favoriteMovie: Movie) = movieLocalDataSource.save(favoriteMovie)
    fun saveAll(favoriteMovie: List<Movie>) = movieLocalDataSource.saveAll(favoriteMovie)
    fun delete(favoriteMovie: Movie) = movieLocalDataSource.delete(favoriteMovie)
    fun deleteAll() = movieLocalDataSource.deleteAll()
    fun deleteAll(favoriteMovie: List<Movie>) = movieLocalDataSource.deleteAll(favoriteMovie)
    fun replaceAll(favoriteMovie: List<Movie>) = movieLocalDataSource.replaceAll(favoriteMovie)
    fun getMoviesDetails(movieID: Int) = movieRemoteDataSource.getMoviesDetails(movieID)
    @Throws(Exception::class)
    suspend fun getAllRemote(withCast: String, withGenres: String) = movieRemoteDataSource.getMovies(withCast, withGenres)
    @Throws(Exception::class)
    suspend fun searchMovies(query: String) = movieRemoteDataSource.searchMovies(query)
}