package com.yogaprasetyo.capstone.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yogaprasetyo.capstone.core.databinding.ItemBannerBinding
import com.yogaprasetyo.capstone.core.domain.model.Movie
import com.yogaprasetyo.capstone.core.utils.Helper.DiffCallback
import com.yogaprasetyo.capstone.core.utils.loadImage


class BannerAdapter(private val itemClicked: (Movie) -> Unit) :
    ListAdapter<Movie, BannerAdapter.BannerViewHolder>(DiffCallback()) {

    private var isFavoritePage = false
    private lateinit var onFavoriteClickCallback: OnIconClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder =
        BannerViewHolder(
            ItemBannerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val movie = getItem(position) as Movie
        holder.bind(movie)
    }

    // Showing icon for favorite page
    fun inFavoritePage() {
        isFavoritePage = true
    }

    // Only use for handle click favorite item
    fun setOnIconClickCallback(onIconClickCallback: OnIconClickCallback) {
        onFavoriteClickCallback = onIconClickCallback
    }

    inner class BannerViewHolder(private val binding: ItemBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.apply {
                val rating = "${movie.rating} (${movie.ratingCount})"
                val fixGenres = movie.genres.replace("null", "Unknown")

                tvItemTitle.text = movie.title
                tvItemGenres.text = fixGenres
                tvItemRating.text = rating
                ivItemBackground.loadImage(itemView.context, movie.backdrop)
                ivItemPoster.loadImage(itemView.context, movie.poster)

                itemView.setOnClickListener { itemClicked(movie) }
                when { // Show and apply action to icon favorite
                    isFavoritePage -> {
                        ibItemFavorite.apply {
                            isVisible = isFavoritePage
                            setOnClickListener {
                                onFavoriteClickCallback.iconClicked(movie, !isFavoritePage)
                            }
                        }
                    }
                }
            }
        }
    }

    interface OnIconClickCallback {
        fun iconClicked(movie: Movie, newState: Boolean)
    }
}