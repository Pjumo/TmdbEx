package com.onblur.tmdbex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.onblur.tmdbex.data.remote.model.MovieData
import com.onblur.tmdbex.databinding.ContentsListItemBinding
import com.onblur.tmdbex.utils.Constants.BASE_IMAGE_URL
import com.onblur.tmdbex.utils.MovieDiffUtil

class PopularMovieAdapter :
    PagingDataAdapter<MovieData, PopularMovieAdapter.PopularMovieViewHolder>(MovieDiffUtil) {

    override fun onBindViewHolder(holder: PopularMovieViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMovieViewHolder {
        return PopularMovieViewHolder(
            ContentsListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class PopularMovieViewHolder(private val binding: ContentsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movieData: MovieData) {
            Glide.with(binding.root).load(BASE_IMAGE_URL + movieData.poster)
                .into(binding.contentsPoster)
            binding.contentsRate.progress = (movieData.voteAverage.toFloat() * 10).toInt()
            binding.contentsTitle.text = movieData.title
        }
    }
}