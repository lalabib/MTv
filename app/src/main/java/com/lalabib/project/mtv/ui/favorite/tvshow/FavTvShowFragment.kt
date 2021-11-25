package com.lalabib.project.mtv.ui.favorite.tvshow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lalabib.project.mtv.data.local.entity.TvShowEntity
import com.lalabib.project.mtv.databinding.FragmentFavTvShowBinding
import com.lalabib.project.mtv.ui.detail.DetailActivity
import com.lalabib.project.mtv.viewmodel.ViewModelFactory

class FavTvShowFragment : Fragment() {

    private var _binding: FragmentFavTvShowBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFavTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[FavTvShowViewModel::class.java]
            val favTvShowAdapter = FavTvShowAdapter()

            viewModel.getFavoriteTvShow().observe(viewLifecycleOwner, { tvShow ->
                favTvShowAdapter.submitList(tvShow)
                binding.progressBar.visibility = View.GONE
            })

            binding.apply {
                rvFavTv.layoutManager = LinearLayoutManager(context)
                rvFavTv.setHasFixedSize(true)
                rvFavTv.adapter = favTvShowAdapter
            }

            favTvShowAdapter.onItemClickCallback = object : FavTvShowAdapter.OnItemClickCallback {
                override fun onItemClicked(tvShow: TvShowEntity) {
                    Intent(activity, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_SHOW, tvShow.id)
                        putExtra(DetailActivity.EXTRA_TYPE, "tvShow")
                        startActivity(this)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}