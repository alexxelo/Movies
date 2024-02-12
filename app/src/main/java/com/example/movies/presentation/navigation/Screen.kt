package com.example.movies.presentation.navigation

sealed class Screen(val route: String) {
  data object MoviesListScreen : Screen("movies_list_screen")
  data object MovieDetailsScreen : Screen("movie_details_screen")
  data object SearchMovieScreen : Screen("movie_details_screen")

}
