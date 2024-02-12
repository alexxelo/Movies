package com.example.movies.presentation.movies_list


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movies.R
import com.example.movies.presentation.movies_list.components.MoviesListItem
import com.example.movies.presentation.navigation.Screen
import com.example.movies.presentation.utils.BottomAppBarMain
import com.example.movies.presentation.utils.TopAppBarMain

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesListScreen(
  navController: NavController,
  viewModel: MoviesListViewModel = hiltViewModel()
) {
  val state = viewModel.state.value
  Scaffold(
    topBar = {
      TopAppBarMain(title = stringResource(id = R.string.popular),
        onSearch = { navController.navigate(Screen.SearchMovieScreen.route) })
    },
    bottomBar = {
      BottomAppBarMain(
        onFavoriteClick = { navController.navigate(Screen.MovieFavoriteScreen.route) },
      )
    }
  ) { paddingValues ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)
    ) {

      LazyColumn(modifier = Modifier.fillMaxSize()) {
        state.movies?.let {
          items(it.items) { movie ->

            MoviesListItem(
              movie = movie,
            ) {
              navController.navigate(Screen.MovieDetailsScreen.route + "/${movie.kinopoiskId}")
            }
          }
        }
      }
      if (state.error.isNotBlank()) {
        Column(
          modifier = Modifier.fillMaxSize(),
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.Center
        ) {
          Image(
            painter = painterResource(id = R.drawable.error),
            contentDescription = stringResource(id = R.string.error_image)
          )
          Text(
            text = state.error,

            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center,
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 20.dp)
          )
          Button(onClick = {
            navController.navigate(Screen.MoviesListScreen.route) {
              popUpTo(Screen.MoviesListScreen.route) {
                inclusive = true
              }
            }
          }) {
            Text(text = stringResource(id = R.string.reload))
          }
        }
      }
      if (state.isLoading) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
      }
    }
  }
}
