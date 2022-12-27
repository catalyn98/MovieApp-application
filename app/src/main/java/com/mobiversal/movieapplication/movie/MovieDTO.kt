package com.mobiversal.movieapplication.movie

import com.mobiversal.movieapplication.genre.GenreDTO
import com.mobiversal.movieapplication.movie.video.VideosDTO
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDTO(
    val id: Int,

    val title: String,

    @Json(name="poster_path")
    val poster_path: String?,

    var release_date: String,

    var overview: String,

    var isFavorite: Boolean?,

    var isWatched: Boolean?,

    var genres: List<GenreDTO>?,

    @Json(name = "videos")
    var videosDTO: VideosDTO?
) {
    override fun toString(): String {
        return "MovieDTO(id=$id, title='$title', poster_path=$poster_path, release_date='$release_date', overview='$overview', isFavorite=$isFavorite, isWatched=$isWatched, genres=$genres, videosDTO=$videosDTO)"
    }
}