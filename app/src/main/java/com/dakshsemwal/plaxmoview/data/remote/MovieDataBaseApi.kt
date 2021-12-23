package com.dakshsemwal.plaxmoview.data.remote

import com.dakshsemwal.plaxmoview.data.remote.dto.MovieDetailsDto
import com.dakshsemwal.plaxmoview.data.remote.dto.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDataBaseApi {

    @GET("/3/trending/movie/day")
    suspend fun getTrendingMovie(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): MovieListDto

    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
    ): MovieDetailsDto

}