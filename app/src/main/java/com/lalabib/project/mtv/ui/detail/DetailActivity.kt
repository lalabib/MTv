package com.lalabib.project.mtv.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lalabib.project.mtv.R
import com.lalabib.project.mtv.data.local.entity.MovieEntity
import com.lalabib.project.mtv.data.local.entity.TvShowEntity
import com.lalabib.project.mtv.databinding.ActivityDetailBinding
import com.lalabib.project.mtv.viewmodel.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var showTitle: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val extras = intent.extras
        if ( extras != null){
            val showId = extras.getString(EXTRA_SHOW)
            showLoading(true)
            if (showId != null) {
                viewModel.setSelectedShow(showId)
                if (extras.getString(EXTRA_TYPE) == "Movie") {
                    viewModel.getDetailMovie().observe(this, {detailMovie ->
                        populateDetailMovie(detailMovie)
                        showLoading(false)
                    })
                } else {
                    viewModel.getDetailTvShow().observe(this, {detailTvShow ->
                        populateDetailTvShow(detailTvShow)
                        showLoading(false)
                    })
                }
            }
        }

        binding.shareIcon.setOnClickListener {
            val shareIntent : Intent = Intent().apply {
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
        binding.detailRelease.text = it.releaseDate
        binding.detailRate.text = it.voteAverage
        binding.detailOverview.text = it.overview

        showTitle = it.title
        supportActionBar?.title = it.title

        Glide.with(this)
            .load(imgUrl + it.posterPath)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                .error(R.drawable.ic_error))
            .into(binding.imgPoster)
    }

    private fun populateDetailTvShow(it: TvShowEntity) {
        val imgUrl = "https://image.tmdb.org/t/p/w500"
        binding.detailTitle.text = it.name
        binding.detailRelease.text = it.firstAirDate
        binding.detailRate.text = it.voteAverage
        binding.detailOverview.text = it.overview

        showTitle = it.name
        supportActionBar?.title = it.name

        Glide.with(this)
            .load(imgUrl + it.posterPath)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                .error(R.drawable.ic_error))
            .into(binding.imgPoster)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
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