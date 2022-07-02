package com.yogaprasetyo.capstone.emovie.core.ui

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.yogaprasetyo.capstone.emovie.core.R
import com.yogaprasetyo.capstone.emovie.core.databinding.ItemGenreBinding
import com.yogaprasetyo.capstone.emovie.core.utils.getGenreByKey

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
                genres[0] -> AppCompatResources.getDrawable(context, R.drawable.ic_action)
                genres[0] -> AppCompatResources.getDrawable(context, R.drawable.ic_adventure)
                genres[0] -> AppCompatResources.getDrawable(context, R.drawable.ic_comedy)
                genres[0] -> AppCompatResources.getDrawable(context, R.drawable.ic_crime)
                genres[0] -> AppCompatResources.getDrawable(context, R.drawable.ic_drama)
                genres[0] -> AppCompatResources.getDrawable(context, R.drawable.ic_family)
                genres[0] -> AppCompatResources.getDrawable(context, R.drawable.ic_horror)
                genres[0] -> AppCompatResources.getDrawable(context, R.drawable.ic_romance)
                genres[0] -> AppCompatResources.getDrawable(context, R.drawable.ic_scifi)
                genres[0] -> AppCompatResources.getDrawable(context, R.drawable.ic_thriller)
                else -> AppCompatResources.getDrawable(context, R.drawable.ic_warning)
            }
        }
    }
}