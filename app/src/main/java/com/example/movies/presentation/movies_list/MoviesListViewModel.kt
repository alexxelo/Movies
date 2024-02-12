package com.example.movies.presentation.movies_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.common.Resource
import com.example.movies.domain.use_cases.GetMoviesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
  private val getMoviesListUseCase: GetMoviesListUseCase
) : ViewModel() {

  private val _state = mutableStateOf(MoviesListState())
  val state: State<MoviesListState> = _state

  init {
    getMovies()
  }

  private fun getMovies() {
    getMoviesListUseCase().onEach { result ->
      when (result) {
        is Resource.Success -> {
          _state.value = MoviesListState(movies = result.data)
        }

        is Resource.Error -> {
          _state.value = MoviesListState(error = result.message ?: "An unexpected error occured")

        }

        is Resource.Loading -> {
          _state.value = MoviesListState(isLoading = true)
        }
      }
    }.launchIn(viewModelScope)
  }
}