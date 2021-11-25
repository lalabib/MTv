package com.lalabib.project.mtv.ui.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.lalabib.project.mtv.data.MtvRepository
import com.lalabib.project.mtv.data.local.entity.TvShowEntity

class FavTvShowViewModel(private val FavTvShowRepo: MtvRepository) : ViewModel() {

    fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>> {
        return FavTvShowRepo.getFavoriteTvShow()
    }
}