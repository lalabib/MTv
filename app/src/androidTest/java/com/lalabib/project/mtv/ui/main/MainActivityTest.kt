package com.lalabib.project.mtv.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.lalabib.project.mtv.R
import com.lalabib.project.mtv.utils.DataDummy
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    private val dummyMovie = DataDummy.generateDummyMovies()
    private val dummyTVShow = DataDummy.generateDummyTvShows()
    private val position = 2

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loadMovies(){
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rvMovie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(position,click()))
        onView(withId(R.id.imgPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.detailTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.detailTitle)).check(matches(withText(dummyMovie[position].Title)))
        onView(withId(R.id.detailGenre)).check(matches(isDisplayed()))
        onView(withId(R.id.detailGenre)).check(matches(withText(dummyMovie[position].genre)))
        onView(withId(R.id.detailRate)).check(matches(isDisplayed()))
        onView(withId(R.id.detailRate)).check(matches(withText(dummyMovie[position].rate)))
        onView(withId(R.id.detailOverview)).check(matches(isDisplayed()))
        onView(withId(R.id.detailOverview)).check(matches(withText(dummyMovie[position].overview)))
        onView(withId(R.id.favorite_icon)).perform(click())
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
        onView(withId(R.id.detailTitle)).check(matches(withText(dummyTVShow[position].Title)))
        onView(withId(R.id.detailGenre)).check(matches(isDisplayed()))
        onView(withId(R.id.detailGenre)).check(matches(withText(dummyTVShow[position].genre)))
        onView(withId(R.id.detailRate)).check(matches(isDisplayed()))
        onView(withId(R.id.detailRate)).check(matches(withText(dummyTVShow[position].rate)))
        onView(withId(R.id.detailOverview)).check(matches(isDisplayed()))
        onView(withId(R.id.detailOverview)).check(matches(withText(dummyTVShow[position].overview)))
        onView(withId(R.id.favorite_icon)).perform(click())
        onView(withId(R.id.share_icon)).perform(click())
    }
}