package com.lalabib.project.mtv.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.lalabib.project.mtv.BuildConfig
import com.lalabib.project.mtv.data.MtvRepository
import com.lalabib.project.mtv.data.local.entity.MovieEntity
import com.lalabib.project.mtv.data.local.entity.TvShowEntity
import com.lalabib.project.mtv.utils.DataDummy
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
    private val dummyMovie = DataDummy.generateDummyMovies().results[0]
    private val dummyTvShow = DataDummy.generateDummyTvShows().results[0]
    private val movieId = dummyMovie.id
    private val tvShowId = dummyTvShow.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mtvRepository: MtvRepository

    @Mock
    private lateinit var detailMovieObserver: Observer<MovieEntity>

    @Mock
    private lateinit var detailTvShowObserver: Observer<TvShowEntity>

    @Before
    fun movieSetUp() {
        viewModel = DetailViewModel(mtvRepository)
        viewModel.setSelectedShow(movieId)
    }

    @Test
    fun getDetailMovie() {
        val movie = MutableLiveData<MovieEntity>()
        movie.value = dummyMovie

        `when`(mtvRepository.getDetailMovie(movieId, secretCode)).thenReturn(movie)
        val movieEntity = viewModel.getDetailMovie().value as MovieEntity
        verify(mtvRepository).getDetailMovie(movieId, secretCode)
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.id, movieEntity.id)
        assertEquals(dummyMovie.title, movieEntity.title)
        assertEquals(dummyMovie.releaseDate, movieEntity.releaseDate)
        assertEquals(dummyMovie.voteAverage, movieEntity.voteAverage)
        assertEquals(dummyMovie.posterPath, movieEntity.posterPath)
        assertEquals(dummyMovie.overview, movieEntity.overview)

        viewModel.getDetailMovie().observeForever(detailMovieObserver)
        verify(detailMovieObserver).onChanged(dummyMovie)
    }

    @Before
    fun tvShowSetUp() {
        viewModel = DetailViewModel(mtvRepository)
        viewModel.setSelectedShow(tvShowId)
    }

    @Test
    fun getDetailTvShow() {
        val tvShow = MutableLiveData<TvShowEntity>()
        tvShow.value = dummyTvShow

        `when`(mtvRepository.getDetailTvShow(tvShowId, secretCode)).thenReturn(tvShow)
        val tvShowEntity = viewModel.getDetailTvShow().value as TvShowEntity
        verify(mtvRepository).getDetailTvShow(tvShowId, secretCode)
        assertNotNull(tvShowEntity)
        assertEquals(dummyTvShow.id, tvShowEntity.id)
        assertEquals(dummyTvShow.name, tvShowEntity.name)
        assertEquals(dummyTvShow.firstAirDate, tvShowEntity.firstAirDate)
        assertEquals(dummyTvShow.voteAverage, tvShowEntity.voteAverage)
        assertEquals(dummyTvShow.posterPath, tvShowEntity.posterPath)
        assertEquals(dummyTvShow.overview, tvShowEntity.overview)

        viewModel.getDetailTvShow().observeForever(detailTvShowObserver)
        verify(detailTvShowObserver).onChanged(dummyTvShow)
    }

    companion object {
        private const val secretCode = BuildConfig.SecretCode
    }
}