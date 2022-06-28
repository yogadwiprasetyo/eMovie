package com.yogaprasetyo.capstone.core.ui

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yogaprasetyo.capstone.core.R
import com.yogaprasetyo.capstone.core.databinding.ItemGenreBinding
import com.yogaprasetyo.capstone.core.utils.getGenreByKey


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
        holder.bind(genre)
    }

    override fun getItemCount(): Int = listGenre.size

    inner class CategoriesViewHolder(private val binding: ItemGenreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(genreId: Int) {
            val drawable = getDrawableByGenre(genreId)
            binding.apply {
                ivIcon.setImageDrawable(drawable)
                tvGenre.text = getGenreByKey(genreId).toString()
            }
            itemView.setOnClickListener { itemClicked(genreId) }
        }

        private fun getDrawableByGenre(genreId: Int): Drawable? {
            val context = itemView.context
            val genres = context.resources.getStringArray(R.array.genres)
            return when (getGenreByKey(genreId)) {
                genres[0] -> context.getDrawable(R.drawable.ic_action)
                genres[1] -> context.getDrawable(R.drawable.ic_adventure)
                genres[2] -> context.getDrawable(R.drawable.ic_comedy)
                genres[3] -> context.getDrawable(R.drawable.ic_crime)
                genres[4] -> context.getDrawable(R.drawable.ic_drama)
                genres[5] -> context.getDrawable(R.drawable.ic_family)
                genres[6] -> context.getDrawable(R.drawable.ic_horror)
                genres[7] -> context.getDrawable(R.drawable.ic_romance)
                genres[8] -> context.getDrawable(R.drawable.ic_scifi)
                genres[9] -> context.getDrawable(R.drawable.ic_thriller)
                else -> context.getDrawable(R.drawable.ic_warning)
            }
        }
    }
}