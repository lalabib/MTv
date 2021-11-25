package com.lalabib.project.mtv.ui.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.lalabib.project.mtv.data.MtvRepository
import com.lalabib.project.mtv.data.local.entity.TvShowEntity
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
class TvViewModelTest {

    private lateinit var viewModel: TvViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvShowRepository: MtvRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var pagedTvShow: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = TvViewModel(tvShowRepository)
    }

    @Test
    fun getTvShows() {
        val dummyTvShow = Resource.success(pagedTvShow)
        `when`(dummyTvShow.data?.size).thenReturn(12)

        val tvShow = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tvShow.value = dummyTvShow

        `when`(tvShowRepository.getTvShow()).thenReturn(tvShow)
        val tvEntities = viewModel.getTvShow().value?.data
        verify(tvShowRepository).getTvShow()
        assertNotNull(tvEntities)
        assertEquals(12, tvEntities?.size)

        viewModel.getTvShow().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }
}