package com.dakshsemwal.plaxmoview.presentation.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.dakshsemwal.plaxmoview.domain.model.Movie

class MovieDC : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(
            oldItem: Movie,
            newItem: Movie
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Movie,
            newItem: Movie
        ): Boolean = oldItem == newItem
    }