package com.lalabib.project.mtv.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.lalabib.project.mtv.BuildConfig
import com.lalabib.project.mtv.data.MtvRepository
import com.lalabib.project.mtv.data.remote.response.MovieResponse
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
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MtvRepository

    @Mock
    private lateinit var observer: Observer<MovieResponse>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun getMovies() {
        val dummyMovie = DataDummy.generateDummyMovies()
        val movie = MutableLiveData<MovieResponse>()
        movie.value = dummyMovie

        `when`(movieRepository.getMovie(secretCode)).thenReturn(movie)
        val movieEntities = viewModel.getMovies().value
        verify(movieRepository).getMovie(secretCode)
        assertNotNull(movieEntities)
        assertEquals(12, movieEntities?.results?.size)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }

    companion object {
        private const val secretCode = BuildConfig.SecretCode
    }
}