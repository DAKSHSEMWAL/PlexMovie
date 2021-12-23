package com.dakshsemwal.plaxmoview.presentation.ui.movie_detail

import androidx.lifecycle.*
import com.dakshsemwal.plaxmoview.common.Resource
import com.dakshsemwal.plaxmoview.data.remote.dto.toMovieDetail
import com.dakshsemwal.plaxmoview.domain.use_case.GetMovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
) : ViewModel() {

    private val _state = MutableLiveData(MovieDetailState())
    val state: LiveData<MovieDetailState> = _state

    fun getMovieById(movieId: Int) {
        getMovieDetailUseCase(movieId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = MovieDetailState(movieData = result.data?.toMovieDetail())
                }
                is Resource.Error -> {
                    _state.value = MovieDetailState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = MovieDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}