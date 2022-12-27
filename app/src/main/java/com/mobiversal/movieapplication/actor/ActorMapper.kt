package com.mobiversal.movieapplication.actor

class ActorMapper {
    fun map(dto: ActorDTO): FavouriteActor {
        return FavouriteActor(
            id = dto.id,
            name = dto.name,
            imgUrl = dto.imgUrl,
            isSelected = false
        )
    }
}