package com.lalabib.project.mtv.data.remote.response

import com.lalabib.project.mtv.data.local.entity.MovieEntity

data class MovieResponse(
    val results: ArrayList<MovieEntity>
)