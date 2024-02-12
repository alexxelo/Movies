package com.example.movies.presentation.movie_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.common.Constants
import com.example.movies.common.Resource
import com.example.movies.domain.use_cases.GetMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
  private val getMovieUseCase: GetMovieUseCase,
  savedStateHandle: SavedStateHandle
) : ViewModel() {
  private val _state = mutableStateOf(MovieInfoState())
  val state: State<MovieInfoState> = _state

  init {
    savedStateHandle.get<Int>(Constants.ID)?.let { id ->
      getMovie(id)
    }
  }
  private fun getMovie(id: Int) {
    getMovieUseCase(id).onEach { result ->
      when (result) {
        is Resource.Success -> {
          _state.value = MovieInfoState(movie = result.data)
        }
        is Resource.Error -> {
          _state.value = MovieInfoState(error = result.message ?: "An unexpected error occured")

        }
        is Resource.Loading -> {
          _state.value = MovieInfoState(isLoading = true)
        }
      }
    }.launchIn(viewModelScope)

  }
}