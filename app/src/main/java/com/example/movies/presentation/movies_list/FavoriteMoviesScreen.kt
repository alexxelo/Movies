package com.example.movies.presentation.movies_list


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movies.R
import com.example.movies.presentation.movies_list.components.FavoriteMoviesList
import com.example.movies.presentation.navigation.Screen
import com.example.movies.presentation.utils.BottomAppBarMain
import com.example.movies.presentation.utils.BottomBarButton
import com.example.movies.presentation.utils.EmptyScreen
import com.example.movies.presentation.utils.ShowError
import com.example.movies.presentation.utils.TopAppBarMain

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteMoviesScreen(
  navController: NavController,
  viewModel: MoviesViewModel = hiltViewModel()
) {

  val state by viewModel.favMovies.collectAsState()
  var isSearching by remember { mutableStateOf(false) }
  var selectedButton by remember { mutableStateOf(BottomBarButton.Favorites) }
  val searchText by viewModel.searchText.collectAsState()

  Scaffold(
    topBar = {
      TopAppBar(
        title = {
          TopAppBarMain(
            text = stringResource(id = R.string.favorite),
            isSearching = isSearching,
            searchText = searchText,
            onSearchToggle = { isSearching = !isSearching },
            onSearchTextChange = { query ->
              viewModel.onSearchTextChange(query, true)
            },
            onClearSearch = {
              isSearching = false
              viewModel.onSearchTextChange("", true)
            },

            )
        },
        actions = {
          IconButton(onClick = { isSearching = !isSearching }) {
            Icon(
              imageVector = Icons.Filled.Search,
              contentDescription = stringResource(id = R.string.search),
              tint = colorResource(id = R.color.blue)
            )
          }

        }
      )
    },
    bottomBar = {
      BottomAppBar(
        actions = {
          BottomAppBarMain(
            modifier = Modifier.weight(1f),
            onPopularClick = {
              navController.navigate(Screen.MoviesListScreen.route)
            },
            selectedButton = selectedButton,
            onButtonSelected = { button ->
              selectedButton = button
            },
          )
        },
        containerColor = Color.Transparent
      )
    }
  ) { padding ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(padding)
    ) {
      when {
        state.isLoading -> {
          CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

        state.error.isNotEmpty() -> {
          ShowError(navController = navController, error = state.error)
        }

        state.movies.isNullOrEmpty() -> {
          if (searchText.isNotBlank()) {
            EmptyScreen(
              modifier = Modifier.fillMaxSize(),
              text = stringResource(id = R.string.empty_search_result)
            )
          } else {
            EmptyScreen(
              modifier = Modifier.fillMaxSize(),
              text = stringResource(id = R.string.empty_favorite_list)
            )
          }
        }

        else -> {
          state.movies?.let { movies ->
            LazyColumn(modifier = Modifier.fillMaxSize()) {
              items(
                items = movies,
                key = { movie -> movie.kinopoiskId }) { movie ->
                FavoriteMoviesList(
                  movieName = movie.nameRu,
                  movieYear = movie.year,
                  movieGenre = movie.genres[0].genre,
                  moviePoster = movie.posterUrl,
                  onMovieClick = {
                    navController.navigate(Screen.MovieDetailsScreen.route + "/${movie.kinopoiskId}")
                  },
                  onMovieLongClick = {
                    viewModel.toggleFavourite(movie.kinopoiskId, movie)
                  },
                )
              }
            }
          }
        }
      }
    }
  }

}
