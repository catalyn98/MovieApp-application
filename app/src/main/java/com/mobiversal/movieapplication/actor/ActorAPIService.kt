package com.mobiversal.movieapplication.actor

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ActorAPIService {
    @GET("person/popular")
    fun getActors(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<ActorsDTO>
}
