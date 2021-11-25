package com.lalabib.project.mtv.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import com.lalabib.project.mtv.R
import com.lalabib.project.mtv.utils.DataDummy
import com.lalabib.project.mtv.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainActivityTest {

    private val dummyMovie = DataDummy.generateDummyMovies()
    private val dummyTVShow = DataDummy.generateDummyTvShows()
    private val position = 2

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovies(){
        onView(withId(R.id.navigation_movie)).perform(click())
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.navigation_movie)).perform(click())
        onView(withId(R.id.rvMovie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(position,click()))
        onView(withId(R.id.imgPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.detailTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.detailRelease)).check(matches(isDisplayed()))
        onView(withId(R.id.detailRate)).check(matches(isDisplayed()))
        onView(withId(R.id.detailOverview)).check(matches(isDisplayed()))
    }

    @Test
    fun loadFavMovie() {
        onView(withId(R.id.navigation_movie)).perform(click())
        onView(withId(R.id.rvMovie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                position,
                click())
        )
        onView(withId(R.id.favorite_icon)).perform(click())
        onView(isRoot()).perform(pressBack())

        onView(withId(R.id.navigation_favorite)).perform(click())
        onView(withId(R.id.rvFavMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.rvFavMovie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.imgPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.detailTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.detailRelease)).check(matches(isDisplayed()))
        onView(withId(R.id.detailRate)).check(matches(isDisplayed()))
        onView(withId(R.id.detailOverview)).check(matches(isDisplayed()))
        onView(withId(R.id.share_icon)).perform(click())
    }

    @Test
    fun loadTvShows(){
        onView(withId(R.id.navigation_tv)).perform(click())
        onView(withId(R.id.rvTv)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTv)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTVShow.size))
    }

    @Test
    fun loadDetailTvShows() {
        onView(withId(R.id.navigation_tv)).perform(click())
        onView(withId(R.id.rvTv)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(position,click()))
        onView(withId(R.id.imgPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.detailTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.detailRelease)).check(matches(isDisplayed()))
        onView(withId(R.id.detailRate)).check(matches(isDisplayed()))
        onView(withId(R.id.detailOverview)).check(matches(isDisplayed()))
    }

    @Test
    fun loadFavTvShow() {
        onView(withId(R.id.navigation_tv)).perform(click())
        onView(withId(R.id.rvTv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                position,
                click()
            )
        )
        onView(withId(R.id.favorite_icon)).perform(click())
        onView(isRoot()).perform(pressBack())

        onView(withId(R.id.navigation_favorite)).perform(click())
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rvFavTv)).check(matches(isDisplayed()))
        onView(withId(R.id.rvFavTv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.imgPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.detailTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.detailRelease)).check(matches(isDisplayed()))
        onView(withId(R.id.detailRate)).check(matches(isDisplayed()))
        onView(withId(R.id.detailOverview)).check(matches(isDisplayed()))
        onView(withId(R.id.share_icon)).perform(click())
    }
}