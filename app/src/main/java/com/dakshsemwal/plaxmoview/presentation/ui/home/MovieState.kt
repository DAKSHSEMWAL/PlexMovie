package com.dakshsemwal.plaxmoview.presentation.ui.home

import com.dakshsemwal.plaxmoview.data.remote.dto.MovieListDto
import com.dakshsemwal.plaxmoview.data.remote.dto.toMovie
import com.dakshsemwal.plaxmoview.domain.model.Movie

data class MovieState(
    val isLoading: Boolean = false,
    val movieData: MovieListDto? = null,
    val error: String = ""
) {
    val movieList: List<Movie>? = movieData?.results?.map { it.toMovie() }
}
