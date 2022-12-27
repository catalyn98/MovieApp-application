package com.mobiversal.movieapplication.movie.video

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

class VideosDTO(val results: List<VideoDTO>?) {
    override fun toString(): String {
        return "VideosDTO(results=$results)"
    }
}