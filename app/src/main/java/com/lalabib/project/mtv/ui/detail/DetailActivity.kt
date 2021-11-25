package com.lalabib.project.mtv.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lalabib.project.mtv.R
import com.lalabib.project.mtv.data.local.entity.MovieEntity
import com.lalabib.project.mtv.data.local.entity.TvShowEntity
import com.lalabib.project.mtv.databinding.ActivityDetailBinding
import com.lalabib.project.mtv.viewmodel.ViewModelFactory
import com.lalabib.project.mtv.vo.Status

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var showTitle: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
        val extras = intent.extras

        if (extras != null) {
            val showId = extras.getString(EXTRA_SHOW)
            if (showId != null) {
                if (extras.getString(EXTRA_TYPE) == "Movie") {
                    viewModel.setSelectedMovie(showId)
                    viewModel.detailMovie.observe(this, { detailMovie ->
                        when (detailMovie.status) {
                            Status.LOADING -> showLoading(true)
                            Status.SUCCESS -> {
                                if (detailMovie != null) {
                                    detailMovie.data?.let { populateDetailMovie(it) }
                                    showLoading(false)

                                    binding.favoriteIcon.setOnClickListener {
                                        viewModel.setFavoriteMovie()
                                    }
                                }
                            }
                            Status.ERROR -> {
                                showLoading(false)
                                Toast.makeText(
                                    applicationContext,
                                    R.string.error_message,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    })
                } else {
                    viewModel.setSelectedTvShow(showId)
                    viewModel.detailTvShow.observe(this, { detailTvShow ->
                        when (detailTvShow.status) {
                            Status.LOADING -> showLoading(true)
                            Status.SUCCESS -> {
                                if (detailTvShow != null) {
                                    detailTvShow.data?.let { populateDetailTvShow(it) }
                                    showLoading(false)

                                    binding.favoriteIcon.setOnClickListener {
                                        viewModel.setFavoriteTvShow()
                                    }
                                }
                            }
                            Status.ERROR -> {
                                showLoading(false)
                                Toast.makeText(
                                    applicationContext,
                                    R.string.error_message,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    })
                }
            }
        }

        binding.shareIcon.setOnClickListener {
            val shareIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, "$showTitle is aired on Cinema.")
            }
            val share = Intent.createChooser(shareIntent, "MTv")
            startActivity(share)
        }
    }

    private fun populateDetailMovie(it: MovieEntity) {
        val imgUrl = "https://image.tmdb.org/t/p/w500"
        binding.detailTitle.text = it.title
        binding.detailRelease.text = it.release_date
        binding.detailRate.text = it.vote_average
        binding.detailOverview.text = it.overview

        showTitle = it.title
        supportActionBar?.title = it.title

        Glide.with(this)
            .load(imgUrl + it.poster_path)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(binding.imgPoster)

        setFavoriteState(it.isFavorite)
    }

    private fun populateDetailTvShow(it: TvShowEntity) {
        val imgUrl = "https://image.tmdb.org/t/p/w500"
        binding.detailTitle.text = it.name
        binding.detailRelease.text = it.first_air_date
        binding.detailRate.text = it.vote_average
        binding.detailOverview.text = it.overview

        showTitle = it.name
        supportActionBar?.title = it.name

        Glide.with(this)
            .load(imgUrl + it.poster_path)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(binding.imgPoster)

        setFavoriteState(it.isFavorite)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun setFavoriteState(state: Boolean) {
        binding.favoriteIcon.isChecked = state
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_SHOW = "extra_show"
        const val EXTRA_TYPE = "extra_type"
    }
}