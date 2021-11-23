package com.lalabib.project.mtv.data

import androidx.lifecycle.LiveData
import com.lalabib.project.mtv.data.local.entity.MovieEntity
import com.lalabib.project.mtv.data.local.entity.TvShowEntity
import com.lalabib.project.mtv.data.remote.response.MovieResponse
import com.lalabib.project.mtv.data.remote.response.TvShowResponse

interface MtvDataSource {

    fun getMovie(secretCode: String): LiveData<MovieResponse>

    fun getTvShow(secretCode: String): LiveData<TvShowResponse>

    fun getDetailMovie(id: String, secretCode: String): LiveData<MovieEntity>

    fun getDetailTvShow(id: String, secretCode: String): LiveData<TvShowEntity>

}