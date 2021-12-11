package com.lalabib.project.mtv.ui.favorite

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.lalabib.project.mtv.R
import com.lalabib.project.mtv.ui.favorite.movie.FavMovieFragment
import com.lalabib.project.mtv.ui.favorite.tvshow.FavTvShowFragment

class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager)
    : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> FavMovieFragment()
            1 -> FavTvShowFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int)
    : CharSequence = mContext.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = 2

    companion object {
        private val TAB_TITLES = intArrayOf(R.string.movie, R.string.tv_shows)
    }
}
