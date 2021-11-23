package com.lalabib.project.mtv.data.local.entity

import com.google.gson.annotations.SerializedName

data class MovieEntity (
    @SerializedName("id")
    var id: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("overview")
    var overview: String,
    @SerializedName("release_date")
    var releaseDate: String,
    @SerializedName("vote_average")
    var voteAverage: String,
    @SerializedName("poster_path")
    var posterPath: String
)