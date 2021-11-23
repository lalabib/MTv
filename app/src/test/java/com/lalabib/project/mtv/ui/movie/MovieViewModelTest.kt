package com.lalabib.project.mtv.ui.movie

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @Before
    fun setUp() {
        viewModel = MovieViewModel()
    }

    @Test
    fun getMovies() {
        val dataEntities = viewModel.getMovies()
        assertNotNull(dataEntities)
        assertEquals(12, dataEntities.size)
    }
}