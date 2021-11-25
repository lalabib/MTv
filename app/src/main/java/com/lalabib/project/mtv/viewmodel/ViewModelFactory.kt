package com.lalabib.project.mtv.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lalabib.project.mtv.data.MtvRepository
import com.lalabib.project.mtv.di.Injection
import com.lalabib.project.mtv.ui.detail.DetailViewModel
import com.lalabib.project.mtv.ui.favorite.movie.FavMovieViewModel
import com.lalabib.project.mtv.ui.favorite.tvshow.FavTvShowViewModel
import com.lalabib.project.mtv.ui.movie.MovieViewModel
import com.lalabib.project.mtv.ui.tv.TvViewModel

class ViewModelFactory private constructor(private val movieRepository: MtvRepository): ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance
                    ?: ViewModelFactory(Injection.provideRepository(context)).apply { instance = this }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(movieRepository) as T
            }
            modelClass.isAssignableFrom(TvViewModel::class.java) -> {
                TvViewModel(movieRepository) as T
            }
            modelClass.isAssignableFrom(FavMovieViewModel::class.java) -> {
                FavMovieViewModel(movieRepository) as T
            }
            modelClass.isAssignableFrom(FavTvShowViewModel::class.java) -> {
                FavTvShowViewModel(movieRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(movieRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel Class: " + modelClass.name)
        }
    }
}