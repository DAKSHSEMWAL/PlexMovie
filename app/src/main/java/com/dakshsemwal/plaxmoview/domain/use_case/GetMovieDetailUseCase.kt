package com.dakshsemwal.plaxmoview.domain.use_case

import android.util.Log
import com.dakshsemwal.plaxmoview.common.Resource
import com.dakshsemwal.plaxmoview.data.remote.dto.MovieDetailsDto
import com.dakshsemwal.plaxmoview.data.remote.dto.MovieListDto
import com.dakshsemwal.plaxmoview.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(private val repository: MovieRepository) {
    operator fun invoke(id:Int): Flow<Resource<MovieDetailsDto>> = flow {
        try {
            emit(Resource.Loading<MovieDetailsDto>())
            val movies = repository.getMovieById(id)
            emit(Resource.Success<MovieDetailsDto>(movies))
        } catch (e: HttpException) {
            emit(Resource.Error<MovieDetailsDto>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<MovieDetailsDto>("Couldn't reach server.Check your internet connection "))
        }
    }
}
