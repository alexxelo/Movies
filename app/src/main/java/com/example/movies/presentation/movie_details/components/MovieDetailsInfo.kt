package com.example.movies.presentation.movie_details.components


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.movies.domain.model.MovieDetail

@Composable
fun MovieDetailsInfo(
  modifier: Modifier = Modifier,
  movie: MovieDetail
) {
  Box(modifier = Modifier) {
    Column {
      Text(text = movie.nameRu)
      //Text(text = movie.)
    }
  }
}

@Preview
@Composable
fun MovieItemInfoPreview(modifier: Modifier = Modifier) {
  //MovieItemInfo(modifier = modifier)
}