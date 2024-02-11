package com.example.movies.presentation.movie_details

import com.example.movies.domain.model.MovieDetail

data class MovieDetailsState (
  val isLoading: Boolean = false,
  val movie: MovieDetail? = null,
  val error: String = ""
)