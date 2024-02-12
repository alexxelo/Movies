package com.example.movies.presentation.movie_details


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movies.R
import com.example.movies.presentation.movie_details.components.MovieDetailsInfo

@Composable
fun MovieDetailsScreen(
  navController: NavController,
  viewModel: MovieDetailsViewModel = hiltViewModel()
) {
  val state = viewModel.state.value

  state.movie?.let { movie ->
    LazyColumn(modifier = Modifier.fillMaxSize()) {
      item {
        Box(modifier = Modifier.fillMaxSize()) {
          AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
              .data(movie.posterUrl)
              .crossfade(true)
              .build(),
            contentDescription = stringResource(id = R.string.poster),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth(),
            error = painterResource(id = R.drawable.ic_broken_image),
            placeholder = painterResource(id = R.drawable.loading_img)
          )
          IconButton(
            onClick = {
              navController.popBackStack()
            },
            modifier = Modifier
              .padding(16.dp)
              .align(Alignment.TopStart)
          ) {
            Icon(
              imageVector = Icons.Default.ArrowBack,
              contentDescription = stringResource(id = R.string.Back),
              tint = colorResource(id = R.color.blue)
            )
          }
        }
        MovieDetailsInfo(movie)
      }
    }
  }
}
