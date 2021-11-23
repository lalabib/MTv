package com.lalabib.project.mtv.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lalabib.project.mtv.data.local.entity.MovieEntity
import com.lalabib.project.mtv.data.local.entity.TvShowEntity
import com.lalabib.project.mtv.data.remote.RemoteDataSource
import com.lalabib.project.mtv.data.remote.response.MovieResponse
import com.lalabib.project.mtv.data.remote.response.TvShowResponse

class MtvRepository private constructor(private val remoteDataSource: RemoteDataSource) : MtvDataSource {

    companion object {
        @Volatile
        private var instance: MtvRepository? = null

        fun getInstance(remoteData: RemoteDataSource): MtvRepository =
            instance ?: synchronized(this) {
                instance ?: MtvRepository(remoteData).apply { instance = this }
                }
    }

    override fun getMovie(secretCode: String): LiveData<MovieResponse> {
        val movie = MutableLiveData<MovieResponse>()
        remoteDataSource.getMovie(secretCode, object : RemoteDataSource.LoadMovieCallback {
            override fun onAllMovieReceived(movieResponse: MovieResponse) {
                movie.postValue(movieResponse)
            }
        })
        return movie
    }

    override fun getTvShow(secretCode: String): LiveData<TvShowResponse> {
        val tvShow = MutableLiveData<TvShowResponse>()
        remoteDataSource.getTvShow(secretCode, object : RemoteDataSource.LoadTvShowCallback {
            override fun onAllTvShowReceived(tvShowResponse: TvShowResponse) {
                tvShow.postValue(tvShowResponse)
            }
        })
        return tvShow
    }

    override fun getDetailMovie(id: String, secretCode: String): LiveData<MovieEntity> {
        val detailMovie = MutableLiveData<MovieEntity>()
        remoteDataSource.getDetailMovie(id, secretCode, object : RemoteDataSource.LoadDetailMovieCallback {
            override fun onDetailMovieReceived(movieEntity: MovieEntity) {
                detailMovie.postValue(movieEntity)
            }
        })
        return detailMovie
    }

    override fun getDetailTvShow(id: String, secretCode: String): LiveData<TvShowEntity> {
        val detailTvShow = MutableLiveData<TvShowEntity>()
        remoteDataSource.getDetailTvShow(id, secretCode, object : RemoteDataSource.LoadDetailTvShowCallback {
            override fun onDetailTvShowReceived(tvShowEntity: TvShowEntity) {
                detailTvShow.postValue(tvShowEntity)
            }
        })
        return detailTvShow
    }
}