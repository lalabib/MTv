package com.lalabib.project.mtv.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.lalabib.project.mtv.data.local.LocalDataSource
import com.lalabib.project.mtv.data.local.entity.MovieEntity
import com.lalabib.project.mtv.data.local.entity.TvShowEntity
import com.lalabib.project.mtv.data.remote.RemoteDataSource
import com.lalabib.project.mtv.utils.AppExecutors
import com.lalabib.project.mtv.utils.DataDummy
import com.lalabib.project.mtv.utils.LiveDataTestUtil
import com.lalabib.project.mtv.utils.PagedListUtils
import com.lalabib.project.mtv.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MtvRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val mtvRepository = FakeMtvRepository(remote, local, appExecutors)

    private val movieResponse = DataDummy.generateDummyMovies()
    private val tvShowResponse = DataDummy.generateDummyTvShows()

    private val movieId = movieResponse[0].id
    private val tvId = tvShowResponse[0].id
    private val detailMovieEntity = DataDummy.getDetailMovie(movieId)
    private val detailTvShowEntity = DataDummy.getDetailTvShow(tvId)

    @Test
    fun getMovies() {
        val dataMovie = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getMovies()).thenReturn(dataMovie)
        mtvRepository.getMovie()

        val movieEntities = Resource.success(PagedListUtils.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getMovies()
        assertNotNull(movieEntities.data)
        assertEquals(movieResponse.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getTvShow() {
        val dataTvShow = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getTvShows()).thenReturn(dataTvShow)
        mtvRepository.getTvShow()

        val tvShowEntities = Resource.success(PagedListUtils.mockPagedList(DataDummy.generateDummyTvShows()))
        verify(local).getTvShows()
        assertNotNull(tvShowEntities.data)
        assertEquals(tvShowResponse.size.toLong(), tvShowEntities.data?.size?.toLong())
    }

    @Test
    fun getDetailMovie() {
        val detailMovie = MutableLiveData<MovieEntity>()
        detailMovie.value = detailMovieEntity
        `when`(local.getDetailMovies(movieId)).thenReturn(detailMovie)

        val movieEntities = LiveDataTestUtil.getValue(mtvRepository.getDetailMovie(movieId))
        verify(local).getDetailMovies(movieId)
        assertNotNull(movieEntities.data)
        assertEquals(movieResponse[0].title, movieEntities.data?.title)
    }

    @Test
    fun getDetailTvShow() {
        val detailTvShow = MutableLiveData<TvShowEntity>()
        detailTvShow.value = detailTvShowEntity
        `when`(local.getDetailTvShows(tvId)).thenReturn(detailTvShow)

        val tvShowEntities = LiveDataTestUtil.getValue(mtvRepository.getDetailTvShow(tvId))
        verify(local).getDetailTvShows(tvId)
        assertNotNull(tvShowEntities.data)
        assertEquals(tvShowResponse[0].name, tvShowEntities.data?.name)
    }

    @Test
    fun getFavoriteMovie() {
        val dataFavMovie = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getFavMovies()).thenReturn(dataFavMovie)
        mtvRepository.getFavoriteMovie()

        val favMovieEntities = Resource.success(PagedListUtils.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getFavMovies()
        assertNotNull(favMovieEntities.data)
        assertEquals(movieResponse.size, favMovieEntities.data?.size)
    }

    @Test
    fun getFavoriteTvShow() {
        val dataFavTvShow = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getFavTvShows()).thenReturn(dataFavTvShow)
        mtvRepository.getFavoriteTvShow()

        val favTvEntities = Resource.success(PagedListUtils.mockPagedList(DataDummy.generateDummyTvShows()))
        verify(local).getFavTvShows()
        assertNotNull(favTvEntities.data)
        assertEquals(tvShowResponse.size, favTvEntities.data?.size)
    }
}