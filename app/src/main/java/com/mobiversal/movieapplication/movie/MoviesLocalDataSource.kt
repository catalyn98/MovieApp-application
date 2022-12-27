package com.mobiversal.movieapplication.movie

import com.mobiversal.movieapplication.dataBase.DataBase

class MoviesLocalDataSource(database: DataBase) {
    private val moviesDao: MovieDAO = database.movieAppDatabase.moviesDao()
    fun getAll() = moviesDao.getAll()
    fun getFavoritesMovies() = moviesDao.getFavoritesMovies()
    fun getWatchedMovies() = moviesDao.getWatchedMovies()
    fun save(favoriteMovie: Movie) = moviesDao.save(favoriteMovie)
    fun saveAll(favoriteMovie: List<Movie>) = moviesDao.saveAll(favoriteMovie)
    fun delete(favoriteMovie: Movie) = moviesDao.delete(favoriteMovie)
    fun deleteAll() = moviesDao.deleteAll()
    fun deleteAll(favoriteMovie: List<Movie>) = moviesDao.deleteAll(favoriteMovie)
    fun deleteFavoriteMovie(favoriteMovie: Movie) = moviesDao.deleteFavoriteMovie(favoriteMovie)
    fun replaceAll(favoriteMovie: List<Movie>) = moviesDao.replaceAll(favoriteMovie)
}