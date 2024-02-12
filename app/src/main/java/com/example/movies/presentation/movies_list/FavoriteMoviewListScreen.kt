package com.example.movies.presentation.movies_list


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.movies.R
import com.example.movies.presentation.navigation.Screen
import com.example.movies.presentation.utils.BottomAppBarMain
import com.example.movies.presentation.utils.TopAppBarMain

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteMoviesListScreen(navController: NavController) {
  Scaffold(
    topBar = {
      TopAppBarMain(title = stringResource(id = R.string.popular),
        onSearch = { navController.navigate(Screen.SearchMovieScreen.route) })
    },

    bottomBar = { BottomAppBarMain()}
  ) { padding ->

    Box(modifier = Modifier.padding(padding)) {

    }
  }
}


@Preview
@Composable
fun FavoriteMoviesListScreenPreview(modifier: Modifier = Modifier) {
}