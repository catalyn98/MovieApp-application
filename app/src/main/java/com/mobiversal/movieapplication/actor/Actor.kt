package com.mobiversal.movieapplication.actor

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "actors")

data class Actor(
    @PrimaryKey
    @NonNull

    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String
) {
    override fun equals(other: Any?) = (other is Actor) && id==other.id

    override fun toString(): String {
        return "Actor(id=$id, name='$name')"
    }
}