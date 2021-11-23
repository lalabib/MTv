package com.lalabib.project.mtv.ui.movie

import androidx.lifecycle.ViewModel
import com.lalabib.project.mtv.data.DataEntity
import com.lalabib.project.mtv.utils.DataDummy

class MovieViewModel : ViewModel() {
    fun getMovies(): List<DataEntity> = DataDummy.generateDummyMovies()
}