package com.onblur.tmdbex.utils

import androidx.recyclerview.widget.DiffUtil
import com.onblur.tmdbex.data.remote.model.MovieData

object MovieDiffUtil : DiffUtil.ItemCallback<MovieData>() {
    override fun areItemsTheSame(oldItem: MovieData, newItem: MovieData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieData, newItem: MovieData): Boolean {
        return oldItem == newItem
    }
}