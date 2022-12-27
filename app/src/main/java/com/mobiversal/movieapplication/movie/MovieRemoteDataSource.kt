package com.mobiversal.movieapplication.movie

import com.mobiversal.movieapplication.network.Constants
import com.mobiversal.movieapplication.network.executeAndDeliver
import retrofit2.Retrofit
import java.lang.Exception

class MovieRemoteDataSource(retrofit: Retrofit) {

    private val apiService : MovieAPIService = retrofit.create(MovieAPIService::class.java)
    private val movieMapper  = MovieMapper()

    @Throws(Exception::class)
    fun getMovies(withCast: String, withGenres: String): List<Movie>{
        return apiService.getMovies(Constants.API_KEY, Constants.LANGUAGE, withCast, withGenres)
            .executeAndDeliver()
            .results
            .map { movieMapper.map(it) }
    }

    @Throws(Exception::class)
    fun searchMovies(query: String): List<Movie>{
        return apiService.searchMovies(Constants.API_KEY, Constants.LANGUAGE, query)
            .executeAndDeliver()
            .results
            .map { movieMapper.map(it) }
    }

    fun getMoviesDetails(movieID: Int) : Movie{
        val dto =  apiService.getMoviesDetails(
            movieID,
            Constants.API_KEY,
            "videos"
        )
            .executeAndDeliver()
            return movieMapper.map(dto)
    }
}