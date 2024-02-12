package com.example.movies.presentation.movie_details


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movies.R

@Composable
fun MovieDetailsScreen(
  viewModel: MovieDetailsViewModel = hiltViewModel()
) {
  val state = viewModel.state.value

  Box(
    modifier = Modifier
      .fillMaxSize()
      .padding()
  ) {
    state.movie?.let { movie ->
      LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
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
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(20.dp),
            horizontalAlignment = Alignment.Start
          ) {

            Text(
              text = movie.nameRu,
              fontWeight = FontWeight.Bold,
              fontSize = 30.sp,
              textAlign = TextAlign.Start,
              modifier = Modifier
            )
            Text(
              text = movie.genres[0].genre, textAlign = TextAlign.Start, color = Color.Gray
            )
            Text(
              text = movie.countries[0].country, textAlign = TextAlign.Start, color = Color.Gray
            )
          }
        }
      }
    }

  }

}

@Preview
@Composable
fun MovieDetailsScreenPreview(modifier: Modifier = Modifier) {
  //MovieDetailsScreen(modifier = modifier)
}