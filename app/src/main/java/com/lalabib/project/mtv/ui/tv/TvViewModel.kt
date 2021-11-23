package com.lalabib.project.mtv.ui.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lalabib.project.mtv.BuildConfig
import com.lalabib.project.mtv.data.MtvRepository
import com.lalabib.project.mtv.data.remote.response.TvShowResponse

class TvViewModel(private val tvShowRepository: MtvRepository) : ViewModel() {
    fun getTvShow(): LiveData<TvShowResponse> = tvShowRepository.getTvShow(secretCode)

    companion object {
        private const val secretCode = BuildConfig.SecretCode
    }
}