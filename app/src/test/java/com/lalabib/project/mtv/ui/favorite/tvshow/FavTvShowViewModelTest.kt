package com.lalabib.project.mtv.ui.favorite.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.lalabib.project.mtv.data.MtvRepository
import com.lalabib.project.mtv.data.local.entity.TvShowEntity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavTvShowViewModelTest {

    private lateinit var viewModel: FavTvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mtvRepository: MtvRepository

    @Mock
    private lateinit var favTvShowObserver: Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var pagedFavTvShow: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = FavTvShowViewModel(mtvRepository)
    }

    @Test
    fun getFavTvShow() {
        val favTvShow = pagedFavTvShow
        Mockito.`when`(favTvShow.size).thenReturn(12)

        val tvShow = MutableLiveData<PagedList<TvShowEntity>>()
        tvShow.value = favTvShow

        Mockito.`when`(mtvRepository.getFavoriteTvShow()).thenReturn(tvShow)
        val tvShowEntities = viewModel.getFavoriteTvShow().value
        Mockito.verify(mtvRepository).getFavoriteTvShow()
        assertNotNull(tvShowEntities)
        assertEquals(12, tvShowEntities?.size)

        viewModel.getFavoriteTvShow().observeForever(favTvShowObserver)
        Mockito.verify(favTvShowObserver).onChanged(favTvShow)
    }
}