package com.example.movies.presentation.movies_list

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.common.Resource
import com.example.movies.data.source.local.FavoriteMovie
import com.example.movies.domain.model.MovieDetail
import com.example.movies.domain.model.MovieInfo
import com.example.movies.domain.model.toFavoriteMovie
import com.example.movies.domain.use_cases.AddMovieUseCase
import com.example.movies.domain.use_cases.GetMovieUseCase
import com.example.movies.domain.use_cases.GetMoviesListUseCase
import com.example.movies.presentation.movie_details.MovieInfoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
  private val getMoviesListUseCase: GetMoviesListUseCase,
  private val addMovieUseCase: AddMovieUseCase,
  private val getMovieUseCase: GetMovieUseCase
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

  fun getMovie(id: Int) {
    viewModelScope.launch {
      getMovieUseCase(id).onEach{ result ->
        when (result) {
          is Resource.Success -> {
            addMovieUseCase(result.data!!.toFavoriteMovie())
          }
          is Resource.Error -> MovieInfoState(error = result.message ?: "An unexpected error occured")

          is Resource.Loading -> MovieInfoState(isLoading = true)
        }
      }.collect()
    }
  }

}