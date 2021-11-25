package com.lalabib.project.mtv.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.lalabib.project.mtv.data.local.entity.MovieEntity
import com.lalabib.project.mtv.data.local.entity.TvShowEntity
import com.lalabib.project.mtv.data.local.room.MtvDao

class LocalDataSource(private val mtvDao: MtvDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(mtvDao: MtvDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(mtvDao)
    }

    fun getMovies(): DataSource.Factory<Int, MovieEntity> = mtvDao.getMovies()

    fun getTvShows(): DataSource.Factory<Int, TvShowEntity> = mtvDao.getTvShows()

    fun getDetailMovies(id: String): LiveData<MovieEntity> = mtvDao.getDetailMovies(id)

    fun getDetailTvShows(id: String): LiveData<TvShowEntity> = mtvDao.getDetailTVShows(id)

    fun insertMovie(movie: List<MovieEntity>) = mtvDao.insertMovies(movie)

    fun insertTvShow(tvShow: List<TvShowEntity>) = mtvDao.insertTvShows(tvShow)

    fun getFavMovies(): DataSource.Factory<Int, MovieEntity> = mtvDao.getFavoriteMovie()

    fun getFavTvShows(): DataSource.Factory<Int, TvShowEntity> = mtvDao.getFavoriteTvShow()

    fun updateMovie(movie: MovieEntity) {
        mtvDao.updateMovies(movie)
    }

    fun updateTvShow(tvShow: TvShowEntity) {
        mtvDao.updateTvShows(tvShow)
    }

    fun setMovieStatus(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        mtvDao.updateMovies(movie)
    }

    fun setTvShowStatus(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.isFavorite = newState
        mtvDao.updateTvShows(tvShow)
    }
}