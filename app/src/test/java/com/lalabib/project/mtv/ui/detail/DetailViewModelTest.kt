package com.lalabib.project.mtv.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.lalabib.project.mtv.data.MtvRepository
import com.lalabib.project.mtv.data.local.entity.MovieEntity
import com.lalabib.project.mtv.data.local.entity.TvShowEntity
import com.lalabib.project.mtv.utils.DataDummy
import com.lalabib.project.mtv.vo.Resource
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val dummyTvShow = DataDummy.generateDummyTvShows()[0]
    private val movieId = dummyMovie.id
    private val tvShowId = dummyTvShow.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mtvRepository: MtvRepository

    @Mock
    private lateinit var detailMovieObserver: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var detailTvShowObserver: Observer<Resource<TvShowEntity>>

    @Before
    fun detailSetUp() {
        viewModel = DetailViewModel(mtvRepository)
        viewModel.setSelectedMovie(movieId)
        viewModel.setSelectedTvShow(tvShowId)
    }

    @Test
    fun getDetailMovie() {
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = Resource.success(DataDummy.generateDummyMovies()[0])
        `when`(mtvRepository.getDetailMovie(movieId)).thenReturn(movie)

        viewModel.detailMovie.observeForever(detailMovieObserver)
        verify(detailMovieObserver).onChanged(movie.value)

        val movieEntity = viewModel.detailMovie.value?.data
        verify(mtvRepository).getDetailMovie(movieId)
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.id, movieEntity?.id)
        assertEquals(dummyMovie.title, movieEntity?.title)
        assertEquals(dummyMovie.release_date, movieEntity?.release_date)
        assertEquals(dummyMovie.vote_average, movieEntity?.vote_average)
        assertEquals(dummyMovie.poster_path, movieEntity?.poster_path)
        assertEquals(dummyMovie.overview, movieEntity?.overview)
    }

    @Test
    fun getDetailTvShow() {
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = Resource.success(DataDummy.generateDummyTvShows()[0])
        `when`(mtvRepository.getDetailTvShow(tvShowId)).thenReturn(tvShow)

        viewModel.detailTvShow.observeForever(detailTvShowObserver)
        verify(detailTvShowObserver).onChanged(tvShow.value)

        val tvShowEntity = viewModel.detailTvShow.value?.data
        verify(mtvRepository).getDetailTvShow(tvShowId)
        assertNotNull(tvShowEntity)
        assertEquals(dummyTvShow.id, tvShowEntity?.id)
        assertEquals(dummyTvShow.name, tvShowEntity?.name)
        assertEquals(dummyTvShow.first_air_date, tvShowEntity?.first_air_date)
        assertEquals(dummyTvShow.vote_average, tvShowEntity?.vote_average)
        assertEquals(dummyTvShow.poster_path, tvShowEntity?.poster_path)
        assertEquals(dummyTvShow.overview, tvShowEntity?.overview)

    }
}