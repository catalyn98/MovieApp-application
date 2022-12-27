package com.mobiversal.movieapplication.genre

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenresDTO (val genres: List<GenreDTO>) {
    override fun toString(): String {
        return "GenresDTO(genres=$genres)"
    }

}