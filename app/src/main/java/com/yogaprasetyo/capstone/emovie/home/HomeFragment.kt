package com.yogaprasetyo.capstone.emovie.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yogaprasetyo.capstone.emovie.R
import com.yogaprasetyo.capstone.emovie.core.data.Resource
import com.yogaprasetyo.capstone.emovie.core.domain.model.Movie
import com.yogaprasetyo.capstone.emovie.core.ui.BannerAdapter
import com.yogaprasetyo.capstone.emovie.core.ui.GenresAdapter
import com.yogaprasetyo.capstone.emovie.core.ui.ThumbnailAdapter
import com.yogaprasetyo.capstone.emovie.core.utils.*
import com.yogaprasetyo.capstone.emovie.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding

    private val trendingAdapter by viewLifecycleLazy { ThumbnailAdapter { moveToDetail(it) } }
    private val nowPlayingAdapter by viewLifecycleLazy { ThumbnailAdapter { moveToDetail(it) } }
    private val topRatedAdapter by viewLifecycleLazy { ThumbnailAdapter { moveToDetail(it) } }
    private val popularAdapter by viewLifecycleLazy { BannerAdapter { moveToDetail(it) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val popular = TypeMovie.POPULAR
        val trending = TypeMovie.TRENDING
        val nowPlaying = TypeMovie.NOW_PLAYING
        val topRated = TypeMovie.TOP_RATED

        homeViewModel.loadMovieByType(popular, mutableMapOf())
            .observe(viewLifecycleOwner) { showResponse(it, popular) }

        homeViewModel.loadMovieByType(trending, mutableMapOf())
            .observe(viewLifecycleOwner) { showResponse(it, trending) }

        homeViewModel.loadMovieByType(nowPlaying, mutableMapOf())
            .observe(viewLifecycleOwner) { showResponse(it, nowPlaying) }

        homeViewModel.loadMovieByType(topRated, mutableMapOf())
            .observe(viewLifecycleOwner) { showResponse(it, topRated) }

        setupGenres()

        listenerMoreMovie(binding?.tvSeeAllTrending, trending)
        listenerMoreMovie(binding?.tvSeeAllNowPlaying, nowPlaying)
        listenerMoreMovie(binding?.tvSeeAllTopRated, topRated)

        setupRecyclerView(binding?.rvPopular, popular)
        setupRecyclerView(binding?.rvTrending, trending)
        setupRecyclerView(binding?.rvNowPlaying, nowPlaying)
        setupRecyclerView(binding?.rvTopRated, topRated)
    }

    private fun showResponse(response: Resource<List<Movie>>, type: TypeMovie) {
        when (response) {
            is Resource.Loading -> showLoading(type)
            is Resource.Error -> showErrorInfo(type)
            is Resource.Success -> showMovies(type, response.data)
        }
    }

    private fun showMovies(type: TypeMovie, data: List<Movie>?) {
        showLoading(type, false)
        if (data.isNullOrEmpty()) {
            showEmptyInfo(type)
            return
        }

        val size = 5
        when (type) {
            TypeMovie.POPULAR -> popularAdapter.submitList(data.preview(size))
            TypeMovie.TRENDING -> trendingAdapter.submitList(data.preview(size))
            TypeMovie.NOW_PLAYING -> nowPlayingAdapter.submitList(data.preview(size))
            TypeMovie.TOP_RATED -> topRatedAdapter.submitList(data.preview(size))
        }
    }

    private fun showEmptyInfo(type: TypeMovie) {
        val message = getString(R.string.empty_info)
        updateTextInfo(message, type)
        showingInfo(true, type)
    }

    private fun showLoading(type: TypeMovie, isShow: Boolean = true) {
        val progressBar = when (type) {
            TypeMovie.POPULAR -> binding?.pbPopular
            TypeMovie.TRENDING -> binding?.pbTrending
            TypeMovie.NOW_PLAYING -> binding?.pbNowPlaying
            TypeMovie.TOP_RATED -> binding?.pbTopRated
        }
        progressBar?.isVisible = isShow
    }

    private fun showErrorInfo(type: TypeMovie) {
        showLoading(type, false)
        val message = getString(R.string.error_info)
        updateTextInfo(message, type)
        showingInfo(true, type)
    }

    private fun showingInfo(hasInfo: Boolean, type: TypeMovie) {
        when (type) {
            TypeMovie.POPULAR -> {
                binding?.tvInfoPopular?.isVisible = hasInfo
                binding?.rvPopular?.isVisible = !hasInfo
            }
            TypeMovie.TRENDING -> {
                binding?.tvInfoTrending?.isVisible = hasInfo
                binding?.rvTrending?.isVisible = !hasInfo
            }
            TypeMovie.NOW_PLAYING -> {
                binding?.tvInfoNowPlaying?.isVisible = hasInfo
                binding?.rvNowPlaying?.isVisible = !hasInfo
            }
            TypeMovie.TOP_RATED -> {
                binding?.tvInfoTopRated?.isVisible = hasInfo
                binding?.rvTopRated?.isVisible = !hasInfo
            }
        }
    }

    private fun updateTextInfo(message: String, type: TypeMovie) {
        val tv = when (type) {
            TypeMovie.POPULAR -> binding?.tvInfoPopular
            TypeMovie.TRENDING -> binding?.tvInfoTrending
            TypeMovie.NOW_PLAYING -> binding?.tvInfoNowPlaying
            TypeMovie.TOP_RATED -> binding?.tvInfoTopRated
        }
        tv?.text = message
    }

    private fun listenerMoreMovie(tv: TextView?, type: TypeMovie) {
        tv?.setOnClickListener {
            val action = HomeFragmentDirections
                .actionNavigationHomeToDetailSectionActivity(typeMovie = type)
            findNavController().navigate(action)
        }
    }

    private fun setupGenres() {
        val rv = binding?.rvGenres as RecyclerView
        val categoriesAdapter = GenresAdapter(loadGenresId()) { movieByGenre(it) }
        val widthItemInDP = 78
        val spanCount = calculateAutoColumns(rv, widthItemInDP)

        rv.apply {
            layoutManager = GridLayoutManager(requireContext(), spanCount)
            adapter = categoriesAdapter
            setHasFixedSize(true)
        }
    }

    private fun movieByGenre(genreId: Int) {
        val action =
            HomeFragmentDirections.actionNavigationHomeToDetailSectionActivity(genreId = genreId)
        findNavController().navigate(action)
    }

    private fun setupRecyclerView(rv: RecyclerView?, typeMovie: TypeMovie) {
        val horizontalLayout = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
        val mAdapter = when (typeMovie) {
            TypeMovie.POPULAR -> popularAdapter
            TypeMovie.TRENDING -> trendingAdapter
            TypeMovie.NOW_PLAYING -> nowPlayingAdapter
            TypeMovie.TOP_RATED -> topRatedAdapter
        }

        rv?.apply {
            layoutManager = horizontalLayout
            adapter = mAdapter
            setHasFixedSize(true)
        }
    }

    private fun moveToDetail(data: Movie) {
        val action = HomeFragmentDirections.actionNavigationHomeToDetailActivity(data)
        findNavController().navigate(action)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                findNavController().navigate(R.id.action_navigation_home_to_searchActivity)
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