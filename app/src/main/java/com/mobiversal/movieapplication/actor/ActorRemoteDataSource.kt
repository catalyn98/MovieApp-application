package com.mobiversal.movieapplication.actor

import com.mobiversal.movieapplication.network.Constants.API_KEY
import com.mobiversal.movieapplication.network.Constants.LANGUAGE
import com.mobiversal.movieapplication.network.executeAndDeliver
import retrofit2.Retrofit
import java.lang.Exception

class ActorRemoteDataSource (retrofit: Retrofit){

    private val apiService : ActorAPIService = retrofit.create(ActorAPIService::class.java)
    private val actorMapper  = ActorMapper()

    @Throws(Exception::class)
    fun getActors(): List<FavouriteActor>{
        return apiService.getActors(API_KEY, LANGUAGE)
            .executeAndDeliver()
            .actors
            .map { actorMapper.map(it) }
    }
}