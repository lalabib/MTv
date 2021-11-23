package com.lalabib.project.mtv.ui.tv

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lalabib.project.mtv.data.local.entity.TvShowEntity
import com.lalabib.project.mtv.databinding.FragmentTvBinding
import com.lalabib.project.mtv.ui.detail.DetailActivity
import com.lalabib.project.mtv.viewmodel.ViewModelFactory

class TvFragment : Fragment() {

    private var _binding: FragmentTvBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTvBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            showLoading(true)

            val factory = ViewModelFactory.getInstance()
            val viewModel = ViewModelProvider(this, factory)[TvViewModel::class.java]

            val tvShowAdapter = TvAdapter()

            viewModel.getTvShow().observe(viewLifecycleOwner, { tvShow ->
                val tvShowEntity = tvShow.results
                tvShowAdapter.setTvShow(tvShowEntity)
                tvShowAdapter.notifyDataSetChanged()
                showLoading(false)
            })

            binding.apply {
                rvTv.layoutManager = LinearLayoutManager(context)
                rvTv.setHasFixedSize(true)
                rvTv.adapter = tvShowAdapter
            }

            tvShowAdapter.onItemClickCallback = object : TvAdapter.OnItemClickCallback {
                override fun onItemClicked(tvShow: TvShowEntity) {
                    Intent(activity, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_SHOW, tvShow.id)
                        putExtra(DetailActivity.EXTRA_TYPE, "TvShow")
                        startActivity(this)
                    }
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}