package com.mobiversal.movieapplication.actor

import com.mobiversal.movieapplication.dataBase.DataBase

class ActorsLocalDataSource(database: DataBase) {
    private val actorsDao: ActorsDao = database.movieAppDatabase.actorsDao()
        fun getAll() = actorsDao.getAll()
        fun save(favoriteActor: FavouriteActor) = actorsDao.save(favoriteActor)
        fun saveAll(favoriteActors: List<FavouriteActor>) = actorsDao.saveAll(favoriteActors)
        fun delete(favoriteActor: FavouriteActor) = actorsDao.delete(favoriteActor)
        fun deleteAll() = actorsDao.deleteAll()
        fun deleteAll(favoriteActors: List<FavouriteActor>) = actorsDao.deleteAll(favoriteActors)
        fun replaceAll(favoriteActors: List<FavouriteActor>) = actorsDao.replaceAll(favoriteActors)
}