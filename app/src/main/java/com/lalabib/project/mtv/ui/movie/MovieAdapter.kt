package com.lalabib.project.mtv.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lalabib.project.mtv.R
import com.lalabib.project.mtv.data.local.entity.MovieEntity
import com.lalabib.project.mtv.databinding.RvMtvBinding

class MovieAdapter : PagedListAdapter<MovieEntity, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {
    var onItemClickCallback: OnItemClickCallback? = null

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
        const val BASE_URL = "https://image.tmdb.org/t/p/w500"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            RvMtvBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie) {
                onItemClickCallback?.onItemClicked(movie)
            }
        }
    }

    class MovieViewHolder(private val binding: RvMtvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity, itemClicked: () -> Unit) {
            with(binding) {
                tvTitle.text = movie.title
                tvRelease.text = movie.release_date
                tvRate.text = movie.vote_average

                Glide.with(itemView.context)
                    .load("$BASE_URL${movie.poster_path}")
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(ivPoster)
                itemView.setOnClickListener { itemClicked.invoke() }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(movie: MovieEntity)
    }
}