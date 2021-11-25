package com.lalabib.project.mtv.ui.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.lalabib.project.mtv.data.MtvRepository
import com.lalabib.project.mtv.data.local.entity.TvShowEntity
import com.lalabib.project.mtv.vo.Resource

class TvViewModel(private val tvShowRepository: MtvRepository) : ViewModel() {
    fun getTvShow(): LiveData<Resource<PagedList<TvShowEntity>>> =
        tvShowRepository.getTvShow()

}