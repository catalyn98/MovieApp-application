package com.mobiversal.movieapplication.movie

import com.mobiversal.movieapplication.genre.GenreMapper
import com.mobiversal.movieapplication.movie.video.VideoMapper

class MovieMapper {

    private val genresMapper = GenreMapper()
    private val videoMapper = VideoMapper()

    fun map(dto: MovieDTO): Movie {
        return Movie(
            id= dto.id,
            title = dto.title,
            poster_path = dto.poster_path,
            release_date = dto.release_date,
            overview = dto.overview,
            isFavorite = dto.isFavorite,
            isWatched = dto.isWatched
        ).apply{
            this.genres = dto.genres?.map {genresMapper.map(it) } ?: emptyList()
            this.videos = dto.videosDTO?.results?.map{videoMapper.map(it)}?: emptyList()
        }
    }
}