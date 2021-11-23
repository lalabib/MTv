package com.lalabib.project.mtv.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lalabib.project.mtv.R
import com.lalabib.project.mtv.data.local.entity.MovieEntity
import com.lalabib.project.mtv.databinding.RvMtvBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    var onItemClickCallback: OnItemClickCallback? = null

    private val listMovie = ArrayList<MovieEntity>()

    fun setMovies(movie: List<MovieEntity>) {
        if (movie.isNullOrEmpty())  return
        this.listMovie.clear()
        this.listMovie.addAll(movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(RvMtvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovie[position]
        holder.bind(movie) {
            onItemClickCallback?.onItemClicked(movie)
        }
    }

    override fun getItemCount(): Int = listMovie.size

    class MovieViewHolder(private val binding: RvMtvBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity, itemClicked: () -> Unit) {
            with(binding){
                tvTitle.text = movie.title
                tvRelease.text = movie.releaseDate
                tvRate.text = movie.voteAverage
                Glide.with(itemView.context)
                    .load("$BASE_URL${movie.posterPath}")
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(ivPoster)
                itemView.setOnClickListener { itemClicked.invoke() }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(movie: MovieEntity)
    }

    companion object {
        const val BASE_URL = "https://image.tmdb.org/t/p/w500"
    }
}