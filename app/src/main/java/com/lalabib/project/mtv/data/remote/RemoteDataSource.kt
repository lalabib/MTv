package com.lalabib.project.mtv.data.remote

import android.util.Log
import com.lalabib.project.mtv.data.local.entity.MovieEntity
import com.lalabib.project.mtv.data.local.entity.TvShowEntity
import com.lalabib.project.mtv.data.remote.response.MovieResponse
import com.lalabib.project.mtv.data.remote.response.TvShowResponse
import com.lalabib.project.mtv.service.ApiClient
import com.lalabib.project.mtv.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                RemoteDataSource().apply { instance = this }
            }

        private const val TAG = "RemoteData"
    }

    fun getMovie(secretCode: String, callback: LoadMovieCallback) {
        EspressoIdlingResource.increment()
        ApiClient.instance.getMovie(secretCode).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { callback.onAllMovieReceived(it) }
                    EspressoIdlingResource.decrement()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e(TAG,"onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getTvShow(secretCode: String, callback: LoadTvShowCallback) {
        EspressoIdlingResource.increment()
        ApiClient.instance.getTvShow(secretCode).enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(call: Call<TvShowResponse>, response: Response<TvShowResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { callback.onAllTvShowReceived(it) }
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                Log.e(TAG,"onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getDetailMovie(id: String, secretCode: String, callback: LoadDetailMovieCallback) {
        EspressoIdlingResource.increment()
        ApiClient.instance.getDetailMovie(id, secretCode).enqueue(object : Callback<MovieEntity> {
            override fun onResponse(call: Call<MovieEntity>, response: Response<MovieEntity>) {
                if (response.isSuccessful) {
                    response.body()?.let { callback.onDetailMovieReceived(it) }
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<MovieEntity>, t: Throwable) {
                Log.e(TAG,"onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getDetailTvShow(id: String, secretCode: String, callback: LoadDetailTvShowCallback) {
        EspressoIdlingResource.increment()
        ApiClient.instance.getDetailTvShow(id, secretCode).enqueue(object : Callback<TvShowEntity> {
            override fun onResponse(call: Call<TvShowEntity>, response: Response<TvShowEntity>) {
                if (response.isSuccessful) {
                    response.body()?.let { callback.onDetailTvShowReceived(it) }
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<TvShowEntity>, t: Throwable) {
                Log.e(TAG,"onFailure: ${t.message.toString()}")
            }
        })
    }

    interface LoadMovieCallback {
        fun onAllMovieReceived(movieResponse: MovieResponse)
    }

    interface LoadTvShowCallback {
        fun onAllTvShowReceived(tvShowResponse: TvShowResponse)
    }

    interface LoadDetailMovieCallback {
        fun onDetailMovieReceived(movieEntity: MovieEntity)
    }

    interface LoadDetailTvShowCallback {
        fun onDetailTvShowReceived(tvShowEntity: TvShowEntity)
    }
}