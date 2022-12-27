package com.mobiversal.movieapplication.genre

class GenreMapper {
    fun map(dto: GenreDTO): Genre{
        return Genre(
            id = dto.id,
            name = dto.name,
            isSelected = false
        )
    }
}