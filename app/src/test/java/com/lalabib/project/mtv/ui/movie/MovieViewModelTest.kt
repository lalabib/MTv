package com.lalabib.project.mtv.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.lalabib.project.mtv.data.MtvRepository
import com.lalabib.project.mtv.data.local.entity.MovieEntity
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
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mtvRepository: MtvRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var pagedMovie: PagedList<MovieEntity>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(mtvRepository)
    }

    @Test
    fun getMovies() {
        val dummyMovie = Resource.success(pagedMovie)
        `when`(dummyMovie.data?.size).thenReturn(12)

        val movie = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movie.value = dummyMovie

        `when`(mtvRepository.getMovie()).thenReturn(movie)
        val movieEntities = viewModel.getMovies().value?.data
        verify(mtvRepository).getMovie()
        assertNotNull(movieEntities)
        assertEquals(12, movieEntities?.size)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
}