package com.mobiversal.movieapplication.movie.video

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class VideoDTO(val key: String,
               val site: String
) {
    override fun toString(): String {
        return "VideosDTO(key='$key', site='$site')"
    }
}