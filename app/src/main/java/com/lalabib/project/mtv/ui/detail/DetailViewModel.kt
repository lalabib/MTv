package com.lalabib.project.mtv.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lalabib.project.mtv.BuildConfig
import com.lalabib.project.mtv.data.MtvRepository
import com.lalabib.project.mtv.data.local.entity.MovieEntity
import com.lalabib.project.mtv.data.local.entity.TvShowEntity

class DetailViewModel(private val movieRepository: MtvRepository): ViewModel() {

    private lateinit var id: String

    fun setSelectedShow(id: String) {
        this.id = id
    }

    fun getDetailMovie(): LiveData<MovieEntity> {
        return movieRepository.getDetailMovie(id, secretCode)
    }
    fun getDetailTvShow(): LiveData<TvShowEntity> {
        return movieRepository.getDetailTvShow(id, secretCode)
    }

    companion object {
        private const val secretCode = BuildConfig.SecretCode
    }
}