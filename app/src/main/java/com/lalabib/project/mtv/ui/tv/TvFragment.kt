package com.lalabib.project.mtv.ui.tv

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lalabib.project.mtv.data.DataEntity
import com.lalabib.project.mtv.databinding.FragmentTvBinding
import com.lalabib.project.mtv.ui.detail.DetailActivity

class TvFragment : Fragment() {

    private var _binding: FragmentTvBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTvBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoading(true)
        if (activity != null) {
            showLoading(false)
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[TvViewModel::class.java]
            val tvShow = viewModel.getTvShow()

            val tvShowAdapter = TvAdapter()
            tvShowAdapter.setTvShow(tvShow)

            binding.apply {
                rvTv.layoutManager = LinearLayoutManager(context)
                rvTv.setHasFixedSize(true)
                rvTv.adapter = tvShowAdapter
            }

            tvShowAdapter.onItemClickCallback = object : TvAdapter.OnItemClickCallback {
                override fun onItemClicked(data: DataEntity) {
                    Intent(activity, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_SHOW, data.id)
                        putExtra(DetailActivity.EXTRA_TYPE,"TvShow")
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