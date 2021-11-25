package com.lalabib.project.mtv.data.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.lalabib.project.mtv.data.local.entity.MovieEntity
import com.lalabib.project.mtv.data.local.entity.TvShowEntity

@Dao
interface MtvDao {

    @Query("SELECT * FROM movie_entities")
    fun getMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tv_entities")
    fun getTvShows(): DataSource.Factory<Int, TvShowEntity>

    @Transaction
    @Query("SELECT * FROM movie_entities WHERE id = :id")
    fun getDetailMovies(id: String): LiveData<MovieEntity>

    @Transaction
    @Query("SELECT * FROM tv_entities WHERE id = :id")
    fun getDetailTVShows(id: String): LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShows: List<TvShowEntity>)

    @Update
    fun updateMovies(movie: MovieEntity)

    @Update
    fun updateTvShows(tvShow: TvShowEntity)

    @Query("SELECT * FROM movie_entities WHERE isFavorite = 1")
    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tv_entities WHERE isFavorite = 1")
    fun getFavoriteTvShow(): DataSource.Factory<Int, TvShowEntity>
}