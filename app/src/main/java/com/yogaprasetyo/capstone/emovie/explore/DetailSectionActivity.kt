package com.yogaprasetyo.capstone.emovie.explore

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.yogaprasetyo.capstone.core.data.Resource
import com.yogaprasetyo.capstone.core.domain.model.Movie
import com.yogaprasetyo.capstone.core.ui.BannerAdapter
import com.yogaprasetyo.capstone.core.utils.getGenreByKey
import com.yogaprasetyo.capstone.emovie.R
import com.yogaprasetyo.capstone.emovie.databinding.ActivityDetailSectionBinding
import com.yogaprasetyo.capstone.emovie.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailSectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailSectionBinding
    private lateinit var bannerAdapter: BannerAdapter

    private val viewModel: DetailSectionViewModel by viewModel()

    private val args: DetailSectionActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = getGenreByKey(args.genreId) ?: args.typeMovie.name.replace("_", " ")
        supportActionBar?.title = getString(R.string.title_explore, title)

        bannerAdapter = BannerAdapter {
            startActivity(
                Intent(this, DetailActivity::class.java)
                    .putExtra(DetailActivity.EXTRA_MOVIE, it)
            )
        }

        viewModel.setArgs(args)
        viewModel.results.observe(this) { showResponse(it) }
        setupRecyclerView()
    }

    private fun showResponse(response: Resource<List<Movie>>) {
        when (response) {
            is Resource.Loading -> showLoading()
            is Resource.Error -> showErrorInfo()
            is Resource.Success -> showMovies(response.data)
        }
    }

    private fun showMovies(data: List<Movie>?) {
        showLoading(false)
        when {
            data.isNullOrEmpty() -> showEmptyInfo()
            else -> bannerAdapter.submitList(data)
        }
    }

    private fun showEmptyInfo() {
        val drawable = getDrawable(R.drawable.ic_empty_search)
        val message = getString(R.string.empty_info)
        updateDrawableAndTextInfo(drawable, message)
        showingInfo(true)
    }

    private fun showLoading(isShow: Boolean = true) {
        binding.progressBar.isVisible = isShow
    }

    private fun showErrorInfo() {
        showLoading(false)
        val drawable = getDrawable(R.drawable.ic_warning)
        val message = getString(R.string.error_info)
        updateDrawableAndTextInfo(drawable, message)
        showingInfo(true)
    }

    private fun showingInfo(hasInfo: Boolean) {
        binding.apply {
            ivInfo.isVisible = hasInfo
            tvInfo.isVisible = hasInfo
        }
    }

    private fun updateDrawableAndTextInfo(drawable: Drawable?, message: String) {
        binding.apply {
            ivInfo.setImageDrawable(drawable)
            tvInfo.text = message
        }
    }

    private fun setupRecyclerView() {
        binding.rvExplore.apply {
            layoutManager = LinearLayoutManager(this@DetailSectionActivity)
            adapter = bannerAdapter
            setHasFixedSize(true)
        }
    }
}