package com.dakshsemwal.plaxmoview.data.remote.dto

import com.dakshsemwal.plaxmoview.domain.model.Movie

data class MovieDto(
    val adult: Boolean,
    val backdrop_path: String?,
    val genre_ids: List<Int>,
    val id: Int,
    val media_type: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String ?,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
    val genres: List<Genre>,
)

fun MovieDto.toMovie() = Movie(id = id, poster_path = poster_path, title = title,backdrop_path = backdrop_path)
