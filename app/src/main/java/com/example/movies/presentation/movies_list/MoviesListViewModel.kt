package com.example.movies.presentation.movies_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
  // use cases
) : ViewModel() {

  private val _state = mutableStateOf(MoviesListState())
  val state: State<MoviesListState> = _state

  private fun getMovies(){
    //use case get movie
  }
}