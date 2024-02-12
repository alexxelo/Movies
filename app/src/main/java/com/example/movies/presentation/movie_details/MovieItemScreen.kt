package com.example.movies.presentation.movie_details


import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MovieDetailsScreen(modifier: Modifier = Modifier) {
  Column(modifier = modifier) {
Text(text = "movie details screen")
  }
}

@Preview
@Composable
fun MovieDetailsScreenPreview(modifier: Modifier = Modifier) {
  MovieDetailsScreen(modifier = modifier)
}