package com.mobiversal.movieapplication.genre

import com.mobiversal.movieapplication.dataBase.DataBase

class GenreLocalDataSource(database: DataBase) {
    private val genreDao: GenreDao = database.movieAppDatabase.genresDao()
    fun getAll() = genreDao.getAll()
    fun save(actor: Genre) = genreDao.save(actor)
    fun saveAll(actors: List<Genre>) = genreDao.saveAll(actors)
    fun delete(actor: Genre) = genreDao.delete(actor)
    fun deleteAll() = genreDao.deleteAll()
    fun deleteAll(actors: List<Genre>) = genreDao.deleteAll(actors)
    fun replaceAll(actors: List<Genre>) = genreDao.replaceAll(actors)
}