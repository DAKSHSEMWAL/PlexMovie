package com.dakshsemwal.plaxmoview.data.remote.dto

import com.dakshsemwal.plaxmoview.domain.model.MovieDetails

data class MovieDetailsDto(
    val adult: Boolean,
    val backdrop_path: String,
    val belongs_to_collection: Any,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

fun MovieDetailsDto.toMovieDetail() =
    MovieDetails(
        id = id,
        adult = adult,
        backdrop_path = backdrop_path,
        overview = overview,
        popularity = popularity,
        poster_path = poster_path,
        production_companies = production_companies,
        release_date = release_date,
        revenue = revenue,
        runtime = runtime,
        status = status,
        title = title,
        vote_average = vote_average,
        vote_count = vote_count,
        genres = genres
    )