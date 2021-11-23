package com.lalabib.project.mtv.ui.tv

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TvViewModelTest {

    private lateinit var viewModel: TvViewModel

    @Before
    fun setUp() {
        viewModel = TvViewModel()
    }

    @Test
    fun getTvShows() {
        val dataEntities = viewModel.getTvShow()
        assertNotNull(dataEntities)
        assertEquals(12, dataEntities.size)
    }
}