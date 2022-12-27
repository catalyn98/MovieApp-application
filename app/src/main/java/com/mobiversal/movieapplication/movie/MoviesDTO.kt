package com.mobiversal.movieapplication.movie

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoviesDTO(
    @Json(name = "results")
    val results: List<MovieDTO>) {
    override fun toString(): String {
        return "MoviesDTO(movies=$results)"
    }
}