package com.example.movies.presentation.movies_list


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movies.R
import com.example.movies.presentation.utils.BottomAppBarMain
import com.example.movies.presentation.utils.TopAppBarMain

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesListScreen(
  modifier: Modifier = Modifier,
  navController: NavController,
  viewModel: MoviesListViewModel = hiltViewModel()
) {
  val state = viewModel.state.value
  Scaffold(
    topBar = { TopAppBarMain(title = stringResource(id = R.string.popular), onSearch = {}) },
    bottomBar = { BottomAppBarMain() }
  ) { paddingValues ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)
    ) {
      LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(state.movies) { movie ->

//          MoviesListItem(
//            movie = movie.items.map { it },
//            isFavorite = false
//          ) {
//            navController.navigate(Screen.MovieDetailsScreen.route + "/${movie.items.map { it.kinopoiskId }}")
//          }

        }
      }
      if (state.error.isNotBlank()) {
        Text(
          text = state.error,
          color = MaterialTheme.colorScheme.error,
          textAlign = TextAlign.Center,
          modifier = Modifier.fillMaxWidth()
        )
      }
      if (state.isLoading){
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
      }
    }
  }
}

@Preview
@Composable
fun MoviesListScreenPreview(modifier: Modifier = Modifier) {
  //MoviesListScreen(modifier = modifier)
}