package com.dakshsemwal.plaxmoview.domain.use_case

import android.util.Log
import com.dakshsemwal.plaxmoview.common.Resource
import com.dakshsemwal.plaxmoview.data.remote.dto.MovieListDto
import com.dakshsemwal.plaxmoview.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(private val repository: MovieRepository) {
    operator fun invoke(page:Int): Flow<Resource<MovieListDto>> = flow {
        try {
            emit(Resource.Loading<MovieListDto>())
            val movies = repository.getTrendingMovie(page)
            emit(Resource.Success<MovieListDto>(movies))
        } catch (e: HttpException) {
            emit(Resource.Error<MovieListDto>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<MovieListDto>("Couldn't reach server.Check your internet connection "))
        }
    }
}
