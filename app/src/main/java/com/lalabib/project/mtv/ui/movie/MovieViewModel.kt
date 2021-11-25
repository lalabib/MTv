package com.lalabib.project.mtv.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.lalabib.project.mtv.data.MtvRepository
import com.lalabib.project.mtv.data.local.entity.MovieEntity
import com.lalabib.project.mtv.vo.Resource

class MovieViewModel(private val movieRepository: MtvRepository) : ViewModel() {

    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> =
        movieRepository.getMovie()
}