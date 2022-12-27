package com.mobiversal.movieapplication.movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.mobiversal.movieapplication.genre.Genre
import com.mobiversal.movieapplication.movie.video.Video
import org.jetbrains.annotations.NotNull

@Entity(tableName = "movies")

data class Movie(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @NotNull
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "poster_path")
    var poster_path: String?,
    @ColumnInfo(name = "release_date")
    var release_date: String,
    @ColumnInfo(name = "overview")
    var overview: String,
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean?,
    @ColumnInfo(name = "isWatched")
    var isWatched: Boolean?
){
    @Ignore
    var genres:List<Genre> = emptyList()
    @Ignore
    var videos: List<Video> = emptyList()
    override fun toString(): String {
        return "Movie(id=$id, title='$title', poster_path=$poster_path, release_date='$release_date', overview='$overview', isFavorite=$isFavorite, isWatched=$isWatched, genres=$genres, videos=$videos)"
    }

}