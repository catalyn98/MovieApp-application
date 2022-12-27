package com.mobiversal.movieapplication.genre

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genres")

data class Genre(
    @PrimaryKey
    @NonNull

    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name="isSelected")
    var isSelected: Boolean= false
) {
    override fun equals(other: Any?) = (other is Genre) && id==other.id

    override fun toString(): String {
        return "FavouriteGenre(id=$id, name='$name')"
    }
}