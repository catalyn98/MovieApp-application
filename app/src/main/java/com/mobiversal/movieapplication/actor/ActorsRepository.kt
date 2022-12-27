package com.mobiversal.movieapplication.actor

import com.mobiversal.movieapplication.dataBase.DataBase
import com.mobiversal.movieapplication.network.APIClient

class ActorsRepository {

    companion object{
        val instance = ActorsRepository()
    }

    private val actorLocalDataSource = ActorsLocalDataSource(
        DataBase.instance
    )

    private val actorRemoteDataSource = ActorRemoteDataSource(
        retrofit = APIClient.instance.retrofit
    )

    suspend fun getAll() = actorLocalDataSource.getAll()
    suspend fun save(actor: FavouriteActor) = actorLocalDataSource.save(actor)
    suspend fun saveAll(actors: List<FavouriteActor>) = actorLocalDataSource.saveAll(actors)
    suspend fun delete(actor: FavouriteActor) = actorLocalDataSource.delete(actor)
    suspend fun deleteAll() = actorLocalDataSource.deleteAll()
    suspend fun deleteAll(favoriteActors: List<FavouriteActor>) = actorLocalDataSource.deleteAll(favoriteActors)
    suspend fun replaceAll(actors: List<FavouriteActor>) = actorLocalDataSource.replaceAll(actors)
    @Throws(Exception::class)
    suspend fun getAllRemote() = actorRemoteDataSource.getActors()
}