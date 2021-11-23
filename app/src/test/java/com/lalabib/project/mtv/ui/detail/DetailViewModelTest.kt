package com.lalabib.project.mtv.ui.detail

import com.lalabib.project.mtv.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val dummyTvShow = DataDummy.generateDummyTvShows()[0]
    private val movieId = dummyMovie.id
    private val tvShowId = dummyTvShow.id

    @Before
    fun movieSetUp() {
        viewModel = DetailViewModel()
        viewModel.setSelectedShow(movieId.toString())
    }

    @Test
    fun getDetailMovie() {
        viewModel.setSelectedShow(dummyMovie.id.toString())
        val movie = viewModel.getMovie()
        assertNotNull(movie)
        assertEquals(dummyMovie.id, movie.id)
        assertEquals(dummyMovie.Title, movie.Title)
        assertEquals(dummyMovie.genre, movie.genre)
        assertEquals(dummyMovie.rate, movie.rate)
        assertEquals(dummyMovie.poster, movie.poster)
        assertEquals(dummyMovie.overview, movie.overview)
    }

    @Before
    fun tvShowSetUp() {
        viewModel = DetailViewModel()
        viewModel.setSelectedShow(tvShowId.toString())
    }

    @Test
    fun getDetailTvShow() {
        viewModel.setSelectedShow(dummyTvShow.id.toString())
        val tvShow = viewModel.getTVShow()
        assertNotNull(tvShow)
        assertEquals(dummyTvShow.id, tvShow.id)
        assertEquals(dummyTvShow.Title, tvShow.Title)
        assertEquals(dummyTvShow.genre, tvShow.genre)
        assertEquals(dummyTvShow.rate, tvShow.rate)
        assertEquals(dummyTvShow.poster, tvShow.poster)
        assertEquals(dummyTvShow.overview, tvShow.overview)
    }




}