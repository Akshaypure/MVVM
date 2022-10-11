package com.openapi.mvvm.ui.main.adapter

import androidx.recyclerview.widget.RecyclerView
import com.openapi.mvvm.data.model.MovieCritics
import com.openapi.mvvm.databinding.ItemCriticsBinding

class MovieCriticsViewHolder(
    private val binding: ItemCriticsBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(movieCritics: MovieCritics) {
        binding.movieCritics = movieCritics
    }
}