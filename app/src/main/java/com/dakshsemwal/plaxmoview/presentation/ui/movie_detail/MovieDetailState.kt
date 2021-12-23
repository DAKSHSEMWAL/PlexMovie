package com.dakshsemwal.plaxmoview.presentation.ui.movie_detail

import com.dakshsemwal.plaxmoview.data.remote.dto.MovieDetailsDto
import com.dakshsemwal.plaxmoview.data.remote.dto.MovieListDto
import com.dakshsemwal.plaxmoview.data.remote.dto.toMovie
import com.dakshsemwal.plaxmoview.domain.model.Movie
import com.dakshsemwal.plaxmoview.domain.model.MovieDetails

data class MovieDetailState(
    val isLoading: Boolean = false,
    val movieData: MovieDetails? = null,
    val error: String = ""
)
