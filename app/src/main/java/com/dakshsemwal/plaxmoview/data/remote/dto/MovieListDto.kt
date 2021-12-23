package com.dakshsemwal.plaxmoview.data.remote.dto

import com.dakshsemwal.plaxmoview.domain.model.Movie

data class MovieListDto(
    val page: Int,
    val results: List<MovieDto>?,
    val total_pages: Int,
    val total_results: Int
){
    val movieList: List<Movie>? = results?.map { it.toMovie() }
}