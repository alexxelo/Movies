package com.example.movies.presentation.movies_list

import com.example.movies.domain.model.MovieInfo
import com.example.movies.domain.model.Movies

data class FavoriteMoviesState(
  val isLoading: Boolean = false,
  val movies: List<MovieInfo>? = null,
  val error: String = ""
)
