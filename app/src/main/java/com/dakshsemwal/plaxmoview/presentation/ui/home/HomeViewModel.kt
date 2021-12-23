package com.dakshsemwal.plaxmoview.presentation.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dakshsemwal.plaxmoview.common.Resource
import com.dakshsemwal.plaxmoview.domain.use_case.GetMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase
) : ViewModel() {

    private val _state = MutableLiveData(MovieState())
    val state: LiveData<MovieState> = _state

    init {
        getMovie(1)
    }

    fun getMovie(page: Int) {
        getMovieUseCase(page).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.e("sfksdf4555k","fdsdfsdfs1212312fsfsfs")
                    _state.value = MovieState(movieData = result.data)
                }
                is Resource.Error -> {
                    Log.e("sfksdf4555k","fdsdfsdfs1212312f")
                    _state.value = MovieState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    Log.e("sfksdf4555k","fdsdfsdfsf")
                    _state.value = MovieState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}