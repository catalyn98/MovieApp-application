package com.mobiversal.movieapplication.dataBase

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mobiversal.movieapplication.actor.ActorsDao
import com.mobiversal.movieapplication.actor.FavouriteActor
import com.mobiversal.movieapplication.genre.Genre
import com.mobiversal.movieapplication.genre.GenreDao
import com.mobiversal.movieapplication.movie.Movie
import com.mobiversal.movieapplication.movie.MovieDAO

class DataBase private constructor(){

    companion object{
        val instance  = DataBase()
    }

    @androidx.room.Database(
        entities = [FavouriteActor::class, Genre::class, Movie::class],
        version = 1
    )

    abstract class MovieAppDatabase : RoomDatabase(){
        abstract fun actorsDao(): ActorsDao
        abstract fun genresDao(): GenreDao
        abstract fun moviesDao(): MovieDAO
    }

    lateinit var movieAppDatabase: MovieAppDatabase
    private set

    fun initialise(context: Context){
        this.movieAppDatabase = Room.databaseBuilder(
            context,
            MovieAppDatabase::class.java,
            "movie_app.db"
        ).build()
    }
}