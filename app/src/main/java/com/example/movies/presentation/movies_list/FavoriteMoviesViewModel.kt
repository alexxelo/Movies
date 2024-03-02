package com.example.movies.presentation.movies_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.common.Resource
import com.example.movies.domain.use_cases.DeleteFavoriteMovieUseCase
import com.example.movies.domain.use_cases.GetMoviesStreamUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FavoriteMoviesViewModel @Inject constructor(
  private val getMoviesStreamUseCase: GetMoviesStreamUseCase,
  private val deleteFromFavoriteUseCase : DeleteFavoriteMovieUseCase,
): ViewModel() {

  private val _state = mutableStateOf(FavoriteMoviesState())
  val state: State<FavoriteMoviesState> = _state

  init {
    getFavoriteMovies()
  }
  private fun getFavoriteMovies(){
    getMoviesStreamUseCase().onEach { result ->
      when (result) {
        is Resource.Success -> {
          _state.value = FavoriteMoviesState(movies = result.data)
        }
        is Resource.Error -> {
          _state.value = FavoriteMoviesState(error = result.message ?: "An unexpected error occured")
        }
        is Resource.Loading -> {
          _state.value = FavoriteMoviesState(isLoading = true)
        }
      }
    }.launchIn(viewModelScope)
  }

  suspend fun deleteMovie(movieId: Int){
    deleteFromFavoriteUseCase(movieId)
  }
}