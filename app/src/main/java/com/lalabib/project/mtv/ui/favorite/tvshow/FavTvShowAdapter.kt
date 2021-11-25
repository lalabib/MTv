package com.lalabib.project.mtv.ui.favorite.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lalabib.project.mtv.R
import com.lalabib.project.mtv.data.local.entity.TvShowEntity
import com.lalabib.project.mtv.databinding.RvMtvBinding

class FavTvShowAdapter : PagedListAdapter<TvShowEntity, FavTvShowAdapter.FavTvViewHolder>(DIFF_CALLBACK) {
    var onItemClickCallback: OnItemClickCallback? = null

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }
        }
        const val BASE_URL = "https://image.tmdb.org/t/p/w500"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavTvViewHolder {
        return FavTvViewHolder(RvMtvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: FavTvViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow) {
                onItemClickCallback?.onItemClicked(tvShow)
            }
        }
    }

    class FavTvViewHolder(private val binding: RvMtvBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowEntity, itemClicked: () -> Unit) {
            with(binding){
                tvTitle.text = tvShow.name
                tvRelease.text = tvShow.first_air_date
                tvRate.text = tvShow.vote_average

                Glide.with(itemView.context)
                    .load("$BASE_URL${tvShow.poster_path}")
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(ivPoster)
                itemView.setOnClickListener { itemClicked.invoke() }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(tvShow: TvShowEntity)
    }
}