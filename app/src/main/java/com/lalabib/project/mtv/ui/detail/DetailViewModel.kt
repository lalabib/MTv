package com.lalabib.project.mtv.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.lalabib.project.mtv.data.MtvRepository
import com.lalabib.project.mtv.data.local.entity.MovieEntity
import com.lalabib.project.mtv.data.local.entity.TvShowEntity
import com.lalabib.project.mtv.vo.Resource

class DetailViewModel(private val movieRepository: MtvRepository) : ViewModel() {

    private val movieId = MutableLiveData<String>()
    private val tvShowId = MutableLiveData<String>()

    fun setSelectedMovie(showId: String) {
        this.movieId.value = showId
    }

    fun setSelectedTvShow(showId: String) {
        this.tvShowId.value = showId
    }

    var detailMovie: LiveData<Resource<MovieEntity>> =
        Transformations.switchMap(movieId) { movieId ->
            movieRepository.getDetailMovie(movieId)
        }

    var detailTvShow: LiveData<Resource<TvShowEntity>> =
        Transformations.switchMap(tvShowId) { tvShowId ->
            movieRepository.getDetailTvShow(tvShowId)
        }

    fun setFavoriteMovie() {
        val movieSource = detailMovie.value
        if (movieSource != null) {
            val movie = movieSource.data
            val newState = !movie?.isFavorite!!

            movieRepository.setFavoriteMovie(movie, newState)
        }
    }

    fun setFavoriteTvShow() {
        val tvShowSource = detailTvShow.value
        if (tvShowSource != null) {
            val tvShow = tvShowSource.data
            val newState = !tvShow?.isFavorite!!

            movieRepository.setFavoriteTvShow(tvShow, newState)
        }
    }
}