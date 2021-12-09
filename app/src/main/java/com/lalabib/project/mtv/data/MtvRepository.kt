package com.lalabib.project.mtv.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.lalabib.project.mtv.data.local.LocalDataSource
import com.lalabib.project.mtv.data.local.entity.MovieEntity
import com.lalabib.project.mtv.data.local.entity.TvShowEntity
import com.lalabib.project.mtv.data.remote.ApiResponse
import com.lalabib.project.mtv.data.remote.RemoteDataSource
import com.lalabib.project.mtv.data.remote.response.DetailMovieResponse
import com.lalabib.project.mtv.data.remote.response.DetailTvShowResponse
import com.lalabib.project.mtv.data.remote.response.MovieResponse
import com.lalabib.project.mtv.data.remote.response.TvShowResponse
import com.lalabib.project.mtv.utils.AppExecutors
import com.lalabib.project.mtv.vo.Resource

class MtvRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
    ) : MtvDataSource {

    override fun getMovie(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, MovieResponse>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(5)
                    .setPageSize(5)
                    .build()
                return LivePagedListBuilder(localDataSource.getMovies(), config).build()
            }
            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<MovieResponse>> =
                remoteDataSource.getMovie()

            public override fun saveCallResult(data: MovieResponse) {
                localDataSource.insertMovie(data.results)
            }
        }.asLiveData()
    }

    override fun getTvShow(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowEntity>, TvShowResponse>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(5)
                    .setPageSize(5)
                    .build()
                return LivePagedListBuilder(localDataSource.getTvShows(), config).build()
            }
            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<TvShowResponse>> =
                remoteDataSource.getTvShow()

            public override fun saveCallResult(data: TvShowResponse) {
                localDataSource.insertTvShow(data.results)
            }
        }.asLiveData()
    }

    override fun getDetailMovie(id: String): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, DetailMovieResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> {
                return localDataSource.getDetailMovies(id)
            }

            override fun shouldFetch(data: MovieEntity?): Boolean {
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<DetailMovieResponse>> {
                return remoteDataSource.getDetailMovie(id)
            }

            override fun saveCallResult(data: DetailMovieResponse) {
                val movie = MovieEntity(
                    data.id,
                    data.title,
                    data.overview,
                    data.releaseDate,
                    data.voteAverage,
                    data.posterPath
                )
                localDataSource.updateMovie(movie)
            }
        }.asLiveData()
    }

    override fun getDetailTvShow(id: String): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, DetailTvShowResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> {
                return localDataSource.getDetailTvShows(id)
            }

            override fun shouldFetch(data: TvShowEntity?): Boolean {
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<DetailTvShowResponse>> {
                return remoteDataSource.getDetailTvShow(id)
            }

            override fun saveCallResult(data: DetailTvShowResponse) {
                val tvShow = TvShowEntity(
                    data.id,
                    data.name,
                    data.overview,
                    data.firstAirDate,
                    data.voteAverage,
                    data.posterPath
                )
                localDataSource.updateTvShow(tvShow)
            }
        }.asLiveData()
    }

    override fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getFavMovies(), config).build()
    }

    override fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getFavTvShows(), config).build()
    }

    override fun setFavoriteMovie(movie: MovieEntity, isFavorite: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setMovieStatus(movie, isFavorite)
        }
    }

    override fun setFavoriteTvShow(tvShow: TvShowEntity, isFavorite: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setTvShowStatus(tvShow, isFavorite)
        }
    }
    
     companion object {
        @Volatile
        private var instance: MtvRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): MtvRepository =
            instance ?: synchronized(this) {
                instance ?: MtvRepository(remoteData, localData, appExecutors ).apply {
                    instance = this
                }
            }
    }
}
