package com.example.movies.presentation.movie_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.common.Constants
import com.example.movies.common.Resource
import com.example.movies.domain.use_cases.GetMovieStreamUseCase
import com.example.movies.domain.use_cases.GetMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
  private val getMovieUseCase: GetMovieUseCase,
  private val getMovieStreamUseCase: GetMovieStreamUseCase,
  savedStateHandle: SavedStateHandle
) : ViewModel() {

  private val _state = MutableStateFlow(MovieInfoState())
  val state: StateFlow<MovieInfoState> = _state.asStateFlow()

  init {
    savedStateHandle.get<Int>(Constants.ID)?.let { id ->
      viewModelScope.launch {
        getMovieStreamUseCase(id).collect { result ->
          when (result) {
            is Resource.Success -> {
              _state.value = MovieInfoState(movie = result.data)
            }

            is Resource.Error -> {
              getMovie(id)
            }

            is Resource.Loading -> {
              _state.value = MovieInfoState(isLoading = true)
            }
          }
        }
      }
    }
  }


  private fun getMovie(id: Int) {
    getMovieUseCase(id).onEach { result ->
      when (result) {
        is Resource.Success -> {
          _state.value = MovieInfoState(movie = result.data)
        }

        is Resource.Error -> {
          _state.value = MovieInfoState(error = result.message ?: "An unexpected error occurred")
        }

        is Resource.Loading -> {
          _state.value = MovieInfoState(isLoading = true)
        }
      }
    }.launchIn(viewModelScope)
  }
}