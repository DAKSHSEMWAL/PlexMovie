package com.dakshsemwal.plaxmoview.domain.repository

import com.dakshsemwal.plaxmoview.data.remote.dto.MovieDetailsDto
import com.dakshsemwal.plaxmoview.data.remote.dto.MovieListDto

interface MovieRepository {

    suspend fun getTrendingMovie(page:Int): MovieListDto

    suspend fun getMovieById(movieId: Int): MovieDetailsDto

}
