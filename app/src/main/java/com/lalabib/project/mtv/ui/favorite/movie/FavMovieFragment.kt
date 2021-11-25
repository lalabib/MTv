package com.lalabib.project.mtv.ui.favorite.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lalabib.project.mtv.data.local.entity.MovieEntity
import com.lalabib.project.mtv.databinding.FragmentFavMovieBinding
import com.lalabib.project.mtv.ui.detail.DetailActivity
import com.lalabib.project.mtv.viewmodel.ViewModelFactory

class FavMovieFragment : Fragment() {

    private var _binding: FragmentFavMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFavMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[FavMovieViewModel::class.java]
            val favMovieAdapter = FavMovieAdapter()

            viewModel.getFavoriteMovie().observe(viewLifecycleOwner, { favMovies ->
                favMovieAdapter.submitList(favMovies)
                binding.progressBar.visibility = View.GONE
            })

            binding.apply {
                rvFavMovie.layoutManager = LinearLayoutManager(context)
                rvFavMovie.setHasFixedSize(true)
                rvFavMovie.adapter = favMovieAdapter
            }

            favMovieAdapter.onItemClickCallback = object : FavMovieAdapter.OnItemClickCallback {
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}