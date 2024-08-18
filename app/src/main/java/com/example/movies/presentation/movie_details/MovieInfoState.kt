package com.example.movies.presentation.movie_details

import com.example.movies.domain.model.MovieInfo

data class MovieInfoState (
  val isLoading: Boolean = false,
  val movie: MovieInfo? = null,
  val error: String? = ""
)