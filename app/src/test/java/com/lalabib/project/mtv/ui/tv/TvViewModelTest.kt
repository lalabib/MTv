package com.lalabib.project.mtv.ui.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.lalabib.project.mtv.BuildConfig
import com.lalabib.project.mtv.data.MtvRepository
import com.lalabib.project.mtv.data.remote.response.TvShowResponse
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
class TvViewModelTest {

    private lateinit var viewModel: TvViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvShowRepository: MtvRepository

    @Mock
    private lateinit var observer: Observer<TvShowResponse>

    @Before
    fun setUp() {
        viewModel = TvViewModel(tvShowRepository)
    }

    @Test
    fun getTvShows() {
        val dummyTvShow = DataDummy.generateDummyTvShows()
        val tvShow = MutableLiveData<TvShowResponse>()
        tvShow.value = dummyTvShow

        `when`(tvShowRepository.getTvShow(secretCode)).thenReturn(tvShow)
        val tvEntities = viewModel.getTvShow().value
        verify(tvShowRepository).getTvShow(secretCode)
        assertNotNull(tvEntities)
        assertEquals(12, tvEntities?.results?.size)

        viewModel.getTvShow().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }

    companion object {
        private const val secretCode = BuildConfig.SecretCode
    }
}