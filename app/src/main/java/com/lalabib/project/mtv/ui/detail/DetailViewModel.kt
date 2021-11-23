package com.lalabib.project.mtv.ui.detail

import androidx.lifecycle.ViewModel
import com.lalabib.project.mtv.data.DataEntity
import com.lalabib.project.mtv.utils.DataDummy

class DetailViewModel: ViewModel() {

    private lateinit var showId: String

    fun setSelectedShow(showId: String) {
        this.showId = showId
    }

    fun getMovie(): DataEntity {
        lateinit var movie: DataEntity
        val moviesEntities = DataDummy.generateDummyMovies()
        for (movieEntity in moviesEntities) {
            if (movieEntity.id == showId) {
                movie = movieEntity
            }
        }
        return movie
    }
    fun getTVShow(): DataEntity {
        lateinit var tvShow: DataEntity
        val tvShowEntities = DataDummy.generateDummyTvShows()
        for (tvShowEntity in tvShowEntities) {
            if (tvShowEntity.id == showId) {
                tvShow = tvShowEntity
            }
        }
        return tvShow
    }


}