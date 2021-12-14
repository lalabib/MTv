package com.lalabib.project.mtv.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lalabib.project.mtv.BuildConfig.SecretCode
import com.lalabib.project.mtv.data.remote.response.DetailMovieResponse
import com.lalabib.project.mtv.data.remote.response.DetailTvShowResponse
import com.lalabib.project.mtv.data.remote.response.MovieResponse
import com.lalabib.project.mtv.data.remote.response.TvShowResponse
import com.lalabib.project.mtv.service.ApiClient
import com.lalabib.project.mtv.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    fun getMovie(): LiveData<ApiResponse<MovieResponse>> {
        EspressoIdlingResource.increment()
        val resultMovie = MutableLiveData<ApiResponse<MovieResponse>>()
        ApiClient.instance.getMovie(SecretCode).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { resultMovie.value = ApiResponse.success(it) }
                    EspressoIdlingResource.decrement()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e(TAG,"onFailure: ${t.message.toString()}")
            }
        })
        return resultMovie
    }

    fun getTvShow(): LiveData<ApiResponse<TvShowResponse>> {
        EspressoIdlingResource.increment()
        val resultTvShow = MutableLiveData<ApiResponse<TvShowResponse>>()
        ApiClient.instance.getTvShow(SecretCode).enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(call: Call<TvShowResponse>, response: Response<TvShowResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { resultTvShow.value = ApiResponse.success(it) }
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                Log.e(TAG,"onFailure: ${t.message.toString()}")
            }
        })
        return resultTvShow
    }

    fun getDetailMovie(id: String): LiveData<ApiResponse<DetailMovieResponse>> {
        EspressoIdlingResource.increment()
        val resultDetailMovie = MutableLiveData<ApiResponse<DetailMovieResponse>>()
        ApiClient.instance.getDetailMovie(id, SecretCode).enqueue(object : Callback<DetailMovieResponse> {
            override fun onResponse(call: Call<DetailMovieResponse>, response: Response<DetailMovieResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { resultDetailMovie.value = ApiResponse.success(it) }
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                Log.e(TAG,"onFailure: ${t.message.toString()}")
            }
        })
        return resultDetailMovie
    }

    fun getDetailTvShow(id: String): LiveData<ApiResponse<DetailTvShowResponse>> {
        EspressoIdlingResource.increment()
        val resultDetailTvShow = MutableLiveData<ApiResponse<DetailTvShowResponse>>()
        ApiClient.instance.getDetailTvShow(id, SecretCode).enqueue(object : Callback<DetailTvShowResponse> {
            override fun onResponse(call: Call<DetailTvShowResponse>, response: Response<DetailTvShowResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { resultDetailTvShow.value = ApiResponse.success(it) }
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<DetailTvShowResponse>, t: Throwable) {
                Log.e(TAG,"onFailure: ${t.message.toString()}")
            }
        })
        return resultDetailTvShow
    }
    
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                RemoteDataSource().apply { instance = this }
            }

        private const val TAG = "RemoteData"
    }
}
