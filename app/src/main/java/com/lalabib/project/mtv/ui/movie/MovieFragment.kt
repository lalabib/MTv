package com.lalabib.project.mtv.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lalabib.project.mtv.R
import com.lalabib.project.mtv.data.local.entity.MovieEntity
import com.lalabib.project.mtv.databinding.FragmentMovieBinding
import com.lalabib.project.mtv.ui.detail.DetailActivity
import com.lalabib.project.mtv.viewmodel.ViewModelFactory
import com.lalabib.project.mtv.vo.Status

class MovieFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
            val movieAdapter = MovieAdapter()

            viewModel.getMovies().observe(viewLifecycleOwner, { movies ->
                if (movies != null) {
                    when (movies.status) {
                        Status.LOADING -> {
                            showLoading(true)
                        }
                        Status.SUCCESS -> {
                            showLoading(false)
                            movieAdapter.submitList(movies.data)
                        }
                        Status.ERROR -> {
                            showLoading(false)
                            Toast.makeText(context, R.string.error_message, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            })

            binding.apply {
                rvMovie.layoutManager = LinearLayoutManager(context)
                rvMovie.setHasFixedSize(true)
                rvMovie.adapter = movieAdapter
            }

            movieAdapter.onItemClickCallback = object : MovieAdapter.OnItemClickCallback {
                override fun onItemClicked(movie: MovieEntity) {
                    Intent(activity, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_SHOW, movie.id)
                        putExtra(DetailActivity.EXTRA_TYPE, "Movie")
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