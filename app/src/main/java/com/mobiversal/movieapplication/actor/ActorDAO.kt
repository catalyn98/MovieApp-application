package com.mobiversal.movieapplication.actor

import androidx.room.*

@Dao
interface ActorDAO {
    @Query("SELECT * FROM actors")
    fun getAll(): List<Actor>
    @Insert
    fun save(actor: Actor)
    @Insert
    fun saveAll(actor: List<Actor>)
    @Delete
    fun delete(actor: Actor)
    @Query("DELETE FROM actors")
    fun deleteAll()
    @Delete
    fun deleteAll(actor: List<Actor>)
    @Transaction
    fun replaceAll(actor: List<Actor>) {
        deleteAll()
        saveAll(actor)
    }
}