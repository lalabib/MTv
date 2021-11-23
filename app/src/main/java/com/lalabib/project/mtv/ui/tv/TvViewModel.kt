package com.lalabib.project.mtv.ui.tv

import androidx.lifecycle.ViewModel
import com.lalabib.project.mtv.data.DataEntity
import com.lalabib.project.mtv.utils.DataDummy

class TvViewModel : ViewModel() {
    fun getTvShow(): List<DataEntity> = DataDummy.generateDummyTvShows()
}