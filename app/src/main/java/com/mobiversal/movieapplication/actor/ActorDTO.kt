package com.mobiversal.movieapplication.actor

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ActorDTO (
    val id: Int,
    val name: String,
    @Json(name="profile_path")
    val imgUrl: String,
    var isSelected: Boolean= false
){
    override fun toString(): String {
        return "ActorsDTO(id=$id, name='$name')"
    }
}