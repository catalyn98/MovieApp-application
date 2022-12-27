package com.mobiversal.movieapplication.actor

import androidx.room.*

@Dao
interface ActorsDao {
    @Query("SELECT * FROM favorite_actors")
    fun getAll(): List<FavouriteActor>
    @Insert
    fun save(favoriteActor: FavouriteActor)
    @Insert
    fun saveAll(favoriteActors: List<FavouriteActor>)
    @Delete
    fun delete(favoriteActor: FavouriteActor)
    @Query("DELETE FROM favorite_actors")
    fun deleteAll()
    @Delete
    fun deleteAll(favoriteActors: List<FavouriteActor>)
    @Delete
    fun deleteFavoriteActor(favoriteActor: FavouriteActor)
    @Transaction
    fun replaceAll(favoriteActors: List<FavouriteActor>) {
        deleteAll()
        saveAll(favoriteActors)
    }
}