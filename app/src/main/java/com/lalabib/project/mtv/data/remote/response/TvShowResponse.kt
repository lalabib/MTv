package com.lalabib.project.mtv.data.remote.response

import com.lalabib.project.mtv.data.local.entity.TvShowEntity

data class TvShowResponse(
    val results: ArrayList<TvShowEntity>
)