package com.yogaprasetyo.capstone.emovie.core.utils

import android.content.Context
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yogaprasetyo.capstone.emovie.core.domain.model.Movie
import okhttp3.CertificatePinner
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Get certificate pinner for domain api.themoviedb.org
 * */
fun getCertificatePinner(): CertificatePinner {
    val pinner = CertificatePinner.Builder()
    pinSHA256.forEach { pinner.add(HOSTNAME, it) }
    return pinner.build()
}

/**
 * Load image from url with Glide library
 * */
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

/**
 * Authors: jamiesanson
 * Source: https://gist.github.com/jamiesanson/478997780eb6ca93361df311058dc5c2
 *
 * A Kotlin lazy implementation which automatically clears itself at
 * appropriate times in the View Lifecycle
 *
 * Menggunakan ini untuk menghindari MemoryLeak pada Fragment untuk property lateinit
 * */
fun <T> Fragment.viewLifecycleLazy(initialise: () -> T): ReadOnlyProperty<Fragment, T> =
    object : ReadOnlyProperty<Fragment, T>, DefaultLifecycleObserver {

        // A backing property to hold our value
        private var binding: T? = null
        private var viewLifecycleOwner: LifecycleOwner? = null

        init {
            // Observe the View Lifecycle of the Fragment
            this@viewLifecycleLazy
                .viewLifecycleOwnerLiveData
                .observe(this@viewLifecycleLazy) { newLifecycleOwner ->
                    viewLifecycleOwner
                        ?.lifecycle
                        ?.removeObserver(this)

                    viewLifecycleOwner = newLifecycleOwner.also {
                        it.lifecycle.addObserver(this)
                    }
                }
        }

        override fun onDestroy(owner: LifecycleOwner) {
            super.onDestroy(owner)
            binding = null
        }

        override fun getValue(
            thisRef: Fragment,
            property: KProperty<*>
        ): T {
            // Return the backing property if it's set, or initialise
            return this.binding ?: initialise().also {
                this.binding = it
            }
        }
    }