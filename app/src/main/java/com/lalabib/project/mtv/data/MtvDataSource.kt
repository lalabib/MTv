package com.lalabib.project.mtv.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.lalabib.project.mtv.data.local.entity.MovieEntity
import com.lalabib.project.mtv.data.local.entity.TvShowEntity
import com.lalabib.project.mtv.vo.Resource

interface MtvDataSource {

    fun getMovie(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getTvShow(): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getDetailMovie(id: String): LiveData<Resource<MovieEntity>>

    fun getDetailTvShow(id: String): LiveData<Resource<TvShowEntity>>

    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>>

    fun setFavoriteMovie(movie: MovieEntity, isFavorite: Boolean)

    fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>>

    fun setFavoriteTvShow(tvShow: TvShowEntity, isFavorite: Boolean)

}