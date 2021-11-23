package com.lalabib.project.mtv.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lalabib.project.mtv.BuildConfig
import com.lalabib.project.mtv.data.remote.RemoteDataSource
import com.lalabib.project.mtv.utils.DataDummy
import com.lalabib.project.mtv.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class MtvRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val movieRepository = FakeMtvRepository(remote)

    private val movieResponse = DataDummy.generateDummyMovies()
    private val tvShowResponse = DataDummy.generateDummyTvShows()

    private val movieId = movieResponse.results[0].id
    private val tvId = tvShowResponse.results[0].id
    private val detailMovieEntity = DataDummy.getDetailMovie(movieId)
    private val detailTvShowEntity = DataDummy.getDetailTvShow(tvId)

    @Test
    fun getMovies() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadMovieCallback)
                .onAllMovieReceived(movieResponse)
            null
        }.`when`(remote).getMovie(eq(secretCode), any())
        val movieEntities = LiveDataTestUtil.getValue(movieRepository.getMovie(secretCode))
        verify(remote).getMovie(eq(secretCode), any())
        assertNotNull(movieEntities.results)
        assertEquals(movieResponse.results.size.toLong(), movieEntities.results.size.toLong())
    }

    @Test
    fun getTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadTvShowCallback)
                .onAllTvShowReceived(tvShowResponse)
            null
        }.`when`(remote).getTvShow(eq(secretCode), any())
        val tvShowEntities = LiveDataTestUtil.getValue(movieRepository.getTvShow(secretCode))
        verify(remote).getTvShow(eq(secretCode), any())
        assertNotNull(tvShowEntities.results)
        assertEquals(tvShowResponse.results.size.toLong(), tvShowEntities.results.size.toLong())
    }

    @Test
    fun getDetailMovie() {
        doAnswer { invocation ->
            (invocation.arguments[2] as RemoteDataSource.LoadDetailMovieCallback)
                .onDetailMovieReceived(detailMovieEntity)
            null
        }.`when`(remote).getDetailMovie(eq(movieId), eq(secretCode), any())
        val movieEntities = LiveDataTestUtil.getValue(movieRepository.getDetailMovie(movieId, secretCode))
        verify(remote).getDetailMovie(eq(movieId), eq(secretCode), any())
        assertNotNull(movieEntities)
        assertEquals(movieResponse.results[0].title, movieEntities.title)
    }

    @Test
    fun getDetailTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[2] as RemoteDataSource.LoadDetailTvShowCallback)
                .onDetailTvShowReceived(detailTvShowEntity)
            null
        }.`when`(remote).getDetailTvShow(eq(tvId), eq(secretCode), any())
        val tvShowEntities = LiveDataTestUtil.getValue(movieRepository.getDetailTvShow(tvId, secretCode))
        verify(remote).getDetailTvShow(eq(tvId), eq(secretCode), any())
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponse.results[0].name, tvShowEntities.name)
    }

    companion object {
        private const val secretCode = BuildConfig.SecretCode
    }
}