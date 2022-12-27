package com.mobiversal.movieapplication.genre

import androidx.room.*

@Dao
interface GenreDao {
    @Query("SELECT * FROM genres")
    fun getAll(): List<Genre>
    @Insert
    fun save(genre: Genre)
    @Insert
    fun saveAll(genre: List<Genre>)
    @Delete
    fun delete(genre: Genre)
    @Query("DELETE FROM genres")
    fun deleteAll()
    @Delete
    fun deleteAll(genre: List<Genre>)
    @Delete
    fun deleteFavoriteActor(genre: Genre)
    @Transaction
    fun replaceAll(genre: List<Genre>) {
        deleteAll()
        saveAll(genre)
    }
}