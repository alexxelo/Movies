package com.example.movies.presentation.movies_list


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import com.example.movies.domain.model.toMovieInfo
import com.example.movies.presentation.movies_list.components.MoviesListItem
import com.example.movies.presentation.navigation.Screen
import com.example.movies.presentation.utils.BottomAppBarMain
import com.example.movies.presentation.utils.BottomBarButton
import com.example.movies.presentation.utils.EmptyScreen
import com.example.movies.presentation.utils.ShowError
import com.example.movies.presentation.utils.TopAppBarMain

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopularMoviesScreen(
  navController: NavController,
  viewModel: MoviesViewModel = hiltViewModel()
) {
  var selectedButton by remember { mutableStateOf(BottomBarButton.Popular) }
  val state by viewModel.allMovies.collectAsState()
  var isSearching by remember { mutableStateOf(false) }
  val searchText by viewModel.searchText.collectAsState()

  Scaffold(
    topBar = {
      TopAppBar(
        title = {
          TopAppBarMain(
            text = stringResource(id = R.string.popular),
            isSearching = isSearching,
            searchText = searchText,
            onSearchToggle = { isSearching = !isSearching },
            onSearchTextChange = { query ->
              viewModel.onSearchTextChange(query, false)
            },
            onClearSearch = {
              isSearching = false
              viewModel.onSearchTextChange("", false)
            }
          )
        },
        actions = {
          if (!isSearching) {
            IconButton(onClick = { isSearching = !isSearching }) {
              Icon(
                imageVector = if (isSearching) Icons.Default.Close else Icons.Filled.Search,
                contentDescription = stringResource(id = R.string.search),
                tint = colorResource(id = R.color.blue)
              )
            }
          }
        }
      )
    },
    bottomBar = {
      BottomAppBar(
        actions = {
          BottomAppBarMain(
            modifier = Modifier.weight(1f),
            onFavoriteClick = { navController.navigate(Screen.MovieFavoriteScreen.route) },
            selectedButton = selectedButton,
            onButtonSelected = { button ->
              selectedButton = button
            },
          )
        },
        containerColor = Color.Transparent
      )
    }
  ) { paddingValues ->

    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)
    ) {
      when {
        state.isLoading -> {
          CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

        state.error.isNotEmpty() -> {
          ShowError(navController = navController, error = state.error)
        }

        state.movies?.items?.isEmpty() == true -> {
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
          state.movies?.let { mov ->
            LazyColumn(modifier = Modifier.fillMaxSize()) {
              items(items = mov.items, key = { movie -> movie.kinopoiskId }) { movie ->
                var isFav by remember(movie.kinopoiskId) {
                  mutableStateOf(
                    viewModel.isFavourite(
                      movie.kinopoiskId
                    )
                  )
                }
                MoviesListItem(
                  movieName = movie.nameRu,
                  movieYear = movie.year,
                  movieGenre = movie.genres[0].genre,
                  moviePoster = movie.posterUrlPreview,
                  isFavorite = isFav,
                  onMovieClick = {
                    navController.navigate(Screen.MovieDetailsScreen.route + "/${movie.kinopoiskId}")
                  },
                  onMovieLongClick = {
                    isFav = !isFav
                    viewModel.toggleFavourite(movie.kinopoiskId, movie.toMovieInfo())

                  }
                )
              }
            }
          }
        }
      }
    }
  }
}
