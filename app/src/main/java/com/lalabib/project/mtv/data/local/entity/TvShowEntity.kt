package com.lalabib.project.mtv.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_entities")
data class TvShowEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "first_air_date")
    val first_air_date: String,

    @ColumnInfo(name = "vote_average")
    val vote_average: String,

    @ColumnInfo(name = "poster_path")
    val poster_path: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)