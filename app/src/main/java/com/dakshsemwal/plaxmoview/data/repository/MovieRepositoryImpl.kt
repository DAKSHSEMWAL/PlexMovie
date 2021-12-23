package com.dakshsemwal.plaxmoview.data.repository

import com.dakshsemwal.plaxmoview.BuildConfig.CONSUMER_KEY
import com.dakshsemwal.plaxmoview.data.remote.MovieDataBaseApi
import com.dakshsemwal.plaxmoview.data.remote.dto.MovieDetailsDto
import com.dakshsemwal.plaxmoview.data.remote.dto.MovieListDto
import com.dakshsemwal.plaxmoview.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val api: MovieDataBaseApi) : MovieRepository {

    private val apiKey = lazy {
        CONSUMER_KEY
    }

    override suspend fun getTrendingMovie(page:Int): MovieListDto {
        return api.getTrendingMovie(apiKey = apiKey.value, page = page)
    }

    override suspend fun getMovieById(movieId: Int): MovieDetailsDto {
        return api.getMovieDetail(movieId, apiKey = apiKey.value)
    }

}