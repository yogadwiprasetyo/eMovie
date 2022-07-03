package com.yogaprasetyo.capstone.emovie.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.navArgs
import com.yogaprasetyo.capstone.emovie.R
import com.yogaprasetyo.capstone.emovie.core.domain.model.Movie
import com.yogaprasetyo.capstone.emovie.core.utils.loadImage
import com.yogaprasetyo.capstone.emovie.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private val detailViewModel: DetailViewModel by viewModel()
    private val args: DetailActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.title_detail)

        val movie = checkingPassingData()
        setupDataToView(movie)
    }

    private fun checkingPassingData(): Movie {
        val intent = intent?.getParcelableExtra<Movie>(EXTRA_MOVIE)
        if (intent != null) return intent // Return data from Activity
        return args.extraMovie // Return data from Fragment
    }

    private fun setupDataToView(movie: Movie) {
        val (_, poster, _, title, releaseDate, rating, ratingCount, genres, overview, _, _, isFavorite) = movie
        val year = releaseDate.split("-")[0]
        val fixGenres = genres.replace("null", "Unknown")

        binding.apply {
            ivPoster.loadImage(this@DetailActivity, poster)
            tvGenres.text = fixGenres
            tvOverview.text = overview
            tvTitle.text = getString(R.string.detail_two_wrap, title, year)
            tvRating.text =
                getString(R.string.detail_two_wrap, rating.toString(), ratingCount.toString())
        }

        var stateFavorite = isFavorite
        setStateFavorite(stateFavorite)

        binding.fab.setOnClickListener {
            stateFavorite = !stateFavorite
            detailViewModel.updateFavoriteMovie(movie, stateFavorite)
            setStateFavorite(stateFavorite)
        }
    }

    private fun setStateFavorite(stateFavorite: Boolean) {
        if (stateFavorite) {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this, R.drawable.ic_favorite_24
                )
            )
        } else {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this, R.drawable.ic_outline_favorite_24
                )
            )
        }
    }

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }
}