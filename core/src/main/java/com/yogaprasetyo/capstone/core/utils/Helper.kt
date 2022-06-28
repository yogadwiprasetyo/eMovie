package com.yogaprasetyo.capstone.core.utils

import android.content.Context
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yogaprasetyo.capstone.core.domain.model.Movie

fun ImageView.loadImage(context: Context, source: Any?) {
    Glide.with(context)
        .load(source)
        .into(this)
}

/**
 * Get all key on map genres
 * */
fun loadGenresId(): List<Int> = genres.keys.toList()

/**
 * Get genre name based on key map
 * */
fun getGenreByKey(key: Int): String? = genres[key]

/**
 * Randomize the list and only take n items
 * */
fun List<Movie>.preview(total: Int) = this.take(total)

/**
 * Calculate number of columns based width of items
 * @return number of columns
 */
fun calculateAutoColumns(rv: RecyclerView, columnWidth: Int): Int {
    val displayMetrics = rv.context.resources.displayMetrics
    return ((displayMetrics.widthPixels / displayMetrics.density) / columnWidth).toInt()
}

/**
 * Class for compare the oldList and newList in ListAdapter
 * */
object Helper {
    class DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.movieId == newItem.movieId
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}