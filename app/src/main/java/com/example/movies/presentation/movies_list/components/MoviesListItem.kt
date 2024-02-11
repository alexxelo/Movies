package com.example.movies.presentation.movies_list.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movies.R
import com.example.movies.domain.model.MovieDetail

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MoviesListItem(
  movie: MovieDetail,
  isFavorite: Boolean = false,
  onMovieClick: (MovieDetail) -> Unit
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .combinedClickable(
        onClick = {
          onMovieClick(movie)
        },
        onLongClick = {
          // TODO(add movie to favorite db )

        }
      )
      .padding(20.dp),
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = stringResource(R.string.poster))
    Column {
      Text(text = movie.nameRu)
      Text(text = "${movie.genres} (${movie.year})")
    }

    Icon(
      imageVector = if (isFavorite) Icons.Filled.FavoriteBorder else Icons.Filled.Favorite,
      contentDescription = stringResource(R.string.favorite)
    )

  }
}


@Preview
@Composable
fun MoviesListItemPreview(modifier: Modifier = Modifier) {
  // MoviesListItem(modifier = modifier)
}