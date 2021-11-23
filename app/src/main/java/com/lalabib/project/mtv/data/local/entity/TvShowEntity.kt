package com.lalabib.project.mtv.data.local.entity

import com.google.gson.annotations.SerializedName

data class TvShowEntity (
    @SerializedName("id")
    var id: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("overview")
    var overview: String,
    @SerializedName("first_air_date")
    var firstAirDate: String,
    @SerializedName("vote_average")
    var voteAverage: String,
    @SerializedName("poster_path")
    var posterPath: String
    )