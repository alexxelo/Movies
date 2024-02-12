package com.example.movies.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movies.presentation.movie_details.MovieDetailsScreen
import com.example.movies.presentation.movies_list.FavoriteMoviesListScreen
import com.example.movies.presentation.movies_list.MoviesListScreen
import com.example.movies.presentation.navigation.Screen
import com.example.movies.presentation.search_movie.SearchMovieScreen
import com.example.movies.presentation.ui.theme.MoviesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MoviesTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          val navController = rememberNavController()
          NavHost(
            navController = navController,
            startDestination = Screen.MoviesListScreen.route
          ) {
            composable(
              route = Screen.MoviesListScreen.route
            ) {
              MoviesListScreen(
                navController = navController,
              )
            }
            composable(
              route = Screen.MovieDetailsScreen.route + "/{id}",
              arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) {
              MovieDetailsScreen(
                navController
              )
            }
            composable(
              route = Screen.SearchMovieScreen.route,
            ) {
              SearchMovieScreen(navController)
            }
            composable(
              route = Screen.MovieFavoriteScreen.route,
            ) {
              FavoriteMoviesListScreen(navController)
            }
          }
        }
      }
    }
  }
}
