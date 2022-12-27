package com.mobiversal.movieapplication.actor

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_actors")

data class FavouriteActor(
    @PrimaryKey
    @NonNull

    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String,

    val imgUrl: String,

    @ColumnInfo(name="isSelected")
    var isSelected: Boolean= false
) {
    override fun equals(other: Any?) = (other is FavouriteActor) && id==other.id

    override fun toString(): String {
        return "FavouriteActor(id=$id, name='$name')"
    }
}