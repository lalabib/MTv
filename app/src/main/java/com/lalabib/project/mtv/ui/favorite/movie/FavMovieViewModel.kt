package com.lalabib.project.mtv.ui.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.lalabib.project.mtv.data.MtvRepository
import com.lalabib.project.mtv.data.local.entity.MovieEntity

class FavMovieViewModel(private val FavMovieRepo: MtvRepository) : ViewModel() {

    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>> {
        return FavMovieRepo.getFavoriteMovie()
    }
}