package com.yogaprasetyo.capstone.core.ui

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yogaprasetyo.capstone.core.R
import com.yogaprasetyo.capstone.core.databinding.ItemGenreBinding
import com.yogaprasetyo.capstone.core.utils.getGenreByKey
import com.yogaprasetyo.capstone.core.utils.loadImage


class GenresAdapter(
    private val listGenre: List<Int>,
    private val itemClicked: (Int) -> Unit
) : RecyclerView.Adapter<GenresAdapter.CategoriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder =
        CategoriesViewHolder(
            ItemGenreBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val genre = listGenre[position]
        holder.bind(genre, itemClicked)
    }

    override fun getItemCount(): Int = listGenre.size

    enum class GenreType(val genre: String) {
        ACTION("Action"), ADVENTURE("Adventure"),
        COMEDY("Comedy"), CRIME("Crime"),
        DRAMA("Drama"), FAMILY("Family"),
        HORROR("Horror"), ROMANCE("Romance"),
        SCIFI("Sci-fi"), THRILLER("Thriller")
    }

    class CategoriesViewHolder(private val binding: ItemGenreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(genreId: Int, itemClicked: (Int) -> Unit) {
            val drawable = getDrawableByGenre(genreId)
            binding.apply {
                ivIcon.loadImage(itemView.context, drawable, false)
                tvGenre.text = getGenreByKey(genreId).toString()
            }
            itemView.setOnClickListener { itemClicked(genreId) }
        }

        private fun getDrawableByGenre(genreId: Int): Drawable? =
            when (getGenreByKey(genreId)) {
                GenreType.ACTION.genre -> itemView.context.getDrawable(R.drawable.ic_action)
                GenreType.ADVENTURE.genre -> itemView.context.getDrawable(R.drawable.ic_adventure)
                GenreType.COMEDY.genre -> itemView.context.getDrawable(R.drawable.ic_comedy)
                GenreType.CRIME.genre -> itemView.context.getDrawable(R.drawable.ic_crime)
                GenreType.DRAMA.genre -> itemView.context.getDrawable(R.drawable.ic_drama)
                GenreType.FAMILY.genre -> itemView.context.getDrawable(R.drawable.ic_family)
                GenreType.HORROR.genre -> itemView.context.getDrawable(R.drawable.ic_horror)
                GenreType.ROMANCE.genre -> itemView.context.getDrawable(R.drawable.ic_romance)
                GenreType.SCIFI.genre -> itemView.context.getDrawable(R.drawable.ic_scifi)
                GenreType.THRILLER.genre -> itemView.context.getDrawable(R.drawable.ic_thriller)
                else -> itemView.context.getDrawable(R.drawable.ic_warning)
            }
    }
}