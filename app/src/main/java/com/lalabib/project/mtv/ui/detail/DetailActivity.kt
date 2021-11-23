package com.lalabib.project.mtv.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lalabib.project.mtv.R
import com.lalabib.project.mtv.data.DataEntity
import com.lalabib.project.mtv.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var showTitle: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailViewModel::class.java]

       showLoading(true)
        val extras = intent.extras
        if (extras != null) {
            val showId = extras.getString(EXTRA_SHOW)
            if (showId != null) {
                viewModel.setSelectedShow(showId)
                if (extras.getString(EXTRA_TYPE) == "Movie") {
                    populateDetail(viewModel.getMovie())
                    showLoading(false)
                } else
                    populateDetail(viewModel.getTVShow())
                    showLoading(false)
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
    }

    private fun populateDetail(it: DataEntity) {
        binding.detailTitle.text = it.Title
        binding.detailGenre.text = it.genre
        binding.detailRate.text = it.rate
        binding.detailOverview.text = it.overview

        showTitle = it.Title
        supportActionBar?.title = it.Title

        Glide.with(this)
            .load(it.poster)
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