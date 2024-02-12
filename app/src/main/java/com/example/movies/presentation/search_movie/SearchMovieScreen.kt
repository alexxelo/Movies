package com.example.movies.presentation.search_movie


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movies.presentation.utils.TopAppBarSearch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchMovieScreen(navController: NavController) {
  Scaffold(
    topBar = { TopAppBarSearch(onSearch = {}, onBack = { navController.popBackStack() }) }
  ) { padding ->

    Column(modifier = Modifier.padding(padding)) {

    }
  }
}
