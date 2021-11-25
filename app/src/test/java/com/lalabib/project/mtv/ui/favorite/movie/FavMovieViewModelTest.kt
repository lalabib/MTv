package com.lalabib.project.mtv.ui.favorite.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.lalabib.project.mtv.data.MtvRepository
import com.lalabib.project.mtv.data.local.entity.MovieEntity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavMovieViewModelTest {

    private lateinit var viewModel: FavMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mtvRepository: MtvRepository

    @Mock
    private lateinit var favMovieObserver: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var pagedFavMovie: PagedList<MovieEntity>

    @Before
    fun setUp() {
        viewModel = FavMovieViewModel(mtvRepository)
    }

    @Test
    fun getFavMovie() {
        val favMovies = pagedFavMovie
        `when`(favMovies.size).thenReturn(12)

        val movies = MutableLiveData<PagedList<MovieEntity>>()
        movies.value = favMovies

        `when`(mtvRepository.getFavoriteMovie()).thenReturn(movies)
        val movieEntities = viewModel.getFavoriteMovie().value
        verify(mtvRepository).getFavoriteMovie()
        assertNotNull(movieEntities)
        assertEquals(12, movieEntities?.size)

        viewModel.getFavoriteMovie().observeForever(favMovieObserver)
        verify(favMovieObserver).onChanged(favMovies)
    }
}