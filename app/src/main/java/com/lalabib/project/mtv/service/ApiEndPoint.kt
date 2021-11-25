package com.lalabib.project.mtv.service

import com.lalabib.project.mtv.data.remote.response.DetailMovieResponse
import com.lalabib.project.mtv.data.remote.response.DetailTvShowResponse
import com.lalabib.project.mtv.data.remote.response.MovieResponse
import com.lalabib.project.mtv.data.remote.response.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndPoint {

    @GET("movie/popular")
    fun getMovie(@Query("api_key") secretCode: String): Call<MovieResponse>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movieId: String,
        @Query("api_key") secretCode: String): Call<DetailMovieResponse>

    @GET("tv/top_rated")
    fun getTvShow(@Query("api_key") secretCode: String): Call<TvShowResponse>

    @GET("tv/{tv_id}")
    fun getDetailTvShow(
        @Path("tv_id") tvId: String,
        @Query("api_key") secretCode: String): Call<DetailTvShowResponse>
}