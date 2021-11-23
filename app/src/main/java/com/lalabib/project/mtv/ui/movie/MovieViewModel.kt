package com.lalabib.project.mtv.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lalabib.project.mtv.BuildConfig
import com.lalabib.project.mtv.data.MtvRepository
import com.lalabib.project.mtv.data.remote.response.MovieResponse

class MovieViewModel(private val movieRepository: MtvRepository) : ViewModel() {

    fun getMovies(): LiveData<MovieResponse> = movieRepository.getMovie(secretCode)

    companion object {
        private const val secretCode = BuildConfig.SecretCode
    }
}