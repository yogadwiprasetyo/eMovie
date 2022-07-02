package com.yogaprasetyo.capstone.emovie.search

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.yogaprasetyo.capstone.emovie.core.data.Resource
import com.yogaprasetyo.capstone.emovie.core.domain.model.Movie
import com.yogaprasetyo.capstone.emovie.core.ui.BannerAdapter
import com.yogaprasetyo.capstone.emovie.core.utils.QUERY_KEY
import com.yogaprasetyo.capstone.emovie.detail.DetailActivity
import com.yogaprasetyo.capstone.emovie.search.databinding.ActivitySearchBinding
import com.yogaprasetyo.capstone.emovie.search.di.searchModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import com.yogaprasetyo.capstone.emovie.R as app

class SearchActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private lateinit var searchView: SearchView
    private lateinit var searchManager: SearchManager
    private lateinit var binding: ActivitySearchBinding
    private lateinit var searchAdapter: BannerAdapter

    private val searchViewModel: SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(searchModule)

        searchAdapter = BannerAdapter {
            startActivity(
                Intent(this, DetailActivity::class.java)
                    .putExtra(DetailActivity.EXTRA_MOVIE, it)
            )
        }

        searchViewModel.results.observe(this) { showResponse(it) }
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
        if (data.isNullOrEmpty()) {
            showEmptyInfo(true)
            return
        }

        showEmptyInfo(false)
        val count = data.size
        binding.tvResults.text = resources.getQuantityString(
            R.plurals.numberOfMovie,
            count,
            count
        )
        binding.tvResults.isVisible = true
        searchAdapter.submitList(data)
    }

    private fun showEmptyInfo(showing: Boolean) {
        val drawable = AppCompatResources.getDrawable(this, app.drawable.ic_empty_search)
        val message = getString(R.string.search_empty_results)
        updateDrawableAndTextInfo(drawable, message)
        showingInfo(showing)
    }

    private fun showLoading(isShow: Boolean = true) {
        binding.progressBar.isVisible = isShow
    }

    private fun showErrorInfo() {
        showLoading(false)
        val drawable = AppCompatResources.getDrawable(this, app.drawable.ic_warning)
        val message = getString(app.string.error_info)
        updateDrawableAndTextInfo(drawable, message)
        showingInfo(true)
    }

    private fun showingInfo(hasInfo: Boolean) {
        binding.apply {
            ivInfoResults.isVisible = hasInfo
            tvInfoResults.isVisible = hasInfo
            tvResults.isVisible = !hasInfo
            rvResults.isVisible = !hasInfo
        }
    }

    private fun updateDrawableAndTextInfo(drawable: Drawable?, message: String) {
        binding.apply {
            ivInfoResults.setImageDrawable(drawable)
            tvInfoResults.text = message
        }
    }

    private fun setupRecyclerView() {
        binding.rvResults.apply {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            adapter = searchAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(app.menu.main_menu, menu)
        val searchItem = menu.findItem(app.id.action_search)
        autoExpandSearchView(searchItem)
        setupSearchView(searchItem)
        return true
    }

    private fun autoExpandSearchView(searchItem: MenuItem) {
        val expandListener = object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(menuItem: MenuItem): Boolean = true
            override fun onMenuItemActionCollapse(menuItem: MenuItem): Boolean {
                onBackPressed()
                return false
            }
        }

        searchItem.setOnActionExpandListener(expandListener)
        searchItem.expandActionView()
    }

    private fun setupSearchView(searchItem: MenuItem) {
        searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = searchItem.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = getString(R.string.search_hint)
        searchView.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query.isNullOrEmpty()) return true

        val queries = mutableMapOf(QUERY_KEY to query)
        searchViewModel.setQuery(queries)
        searchView.clearFocus()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean = false
}