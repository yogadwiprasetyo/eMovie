package com.yogaprasetyo.capstone.emovie.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.yogaprasetyo.capstone.emovie.R
import com.yogaprasetyo.capstone.emovie.core.domain.model.Movie
import com.yogaprasetyo.capstone.emovie.core.ui.BannerAdapter
import com.yogaprasetyo.capstone.emovie.core.utils.viewLifecycleLazy
import com.yogaprasetyo.capstone.emovie.favorite.databinding.FragmentFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private val favoriteAdapter by viewLifecycleLazy { BannerAdapter { moveToDetail(it) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(favoriteModule)

        val favoriteClickCallback = object : BannerAdapter.OnIconClickCallback {
            override fun iconClicked(movie: Movie, newState: Boolean) {
                favoriteViewModel.updateFavoriteMovie(movie, newState) // Remove favorite movie
            }
        }

        favoriteAdapter.inFavoritePage()
        favoriteAdapter.setOnIconClickCallback(favoriteClickCallback)

        favoriteViewModel.loadFavoriteMovies().observe(viewLifecycleOwner) { showFavorite(it) }

        binding?.rvFavorites?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = favoriteAdapter
        }
    }

    private fun showFavorite(listMovie: List<Movie>?) {
        if (listMovie.isNullOrEmpty()) {
            showEmptyInfo(true)
            return
        }

        showEmptyInfo(false)
        favoriteAdapter.submitList(listMovie)
    }

    private fun showEmptyInfo(isEmpty: Boolean) {
        binding?.apply {
            ivEmptyFavorite.isVisible = isEmpty
            tvEmptyFavorite.isVisible = isEmpty
            rvFavorites.isVisible = !isEmpty
        }
    }

    private fun moveToDetail(movie: Movie) {
        val action = FavoriteFragmentDirections.actionNavigationFavoriteToDetailActivity(movie)
        findNavController().navigate(action)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                findNavController().navigate(R.id.action_navigation_favorite_to_searchActivity)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}