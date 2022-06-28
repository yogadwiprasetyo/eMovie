package com.yogaprasetyo.capstone.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yogaprasetyo.capstone.core.databinding.ItemThumbnailBinding
import com.yogaprasetyo.capstone.core.domain.model.Movie
import com.yogaprasetyo.capstone.core.utils.Helper.DiffCallback
import com.yogaprasetyo.capstone.core.utils.loadImage

class ThumbnailAdapter(
    private val itemClicked: (Movie) -> Unit
) : ListAdapter<Movie, ThumbnailAdapter.ThumbnailViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThumbnailViewHolder =
        ThumbnailViewHolder(
            ItemThumbnailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ThumbnailViewHolder, position: Int) {
        val movie = getItem(position) as Movie
        holder.bind(movie)
    }

    inner class ThumbnailViewHolder(private val binding: ItemThumbnailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.apply {
                val year = movie.releaseDate.split("-")[0]
                ivThumbnailPoster.loadImage(itemView.context, movie.backdrop)
                tvThumbnailTitle.text = movie.title
                tvThumbnailYear.text = year
                tvThumbnailRating.text = movie.rating.toString()

                itemView.setOnClickListener { itemClicked(movie) }
            }
        }
    }
}