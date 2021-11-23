package com.lalabib.project.mtv.ui.tv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lalabib.project.mtv.R
import com.lalabib.project.mtv.data.DataEntity
import com.lalabib.project.mtv.databinding.RvMtvBinding

class TvAdapter: RecyclerView.Adapter<TvAdapter.TvViewHolder>() {
    var onItemClickCallback: OnItemClickCallback? = null

    private val listTvShow = ArrayList<DataEntity>()

    fun setTvShow(tv: List<DataEntity>?) {
        if (tv == null)  return
        this.listTvShow.clear()
        this.listTvShow.addAll(tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        return TvViewHolder(RvMtvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        val tvShow = listTvShow[position]
        holder.bind(tvShow) {
            onItemClickCallback?.onItemClicked(tvShow)
        }
    }

    override fun getItemCount(): Int = listTvShow.size

    class TvViewHolder(private val binding: RvMtvBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: DataEntity, itemClicked: () -> Unit) {
            with(binding){
                tvTitle.text = tvShow.Title
                tvGenre.text = tvShow.genre
                tvRate.text = tvShow.rate
                Glide.with(itemView.context)
                    .load(tvShow.poster)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(ivPoster)
                itemView.setOnClickListener { itemClicked.invoke() }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: DataEntity)
    }
}