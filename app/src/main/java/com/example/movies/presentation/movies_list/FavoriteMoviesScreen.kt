package com.example.movies.presentation.movies_list


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movies.R
import com.example.movies.domain.model.MovieDetail
import com.example.movies.domain.model.MovieInfo
import com.example.movies.presentation.movies_list.components.MoviesListItem
import com.example.movies.presentation.navigation.Screen
import com.example.movies.presentation.utils.BottomAppBarMain
import com.example.movies.presentation.utils.TopAppBarMain
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteMoviesScreen(navController: NavController, viewModel: FavoriteMoviesViewModel = hiltViewModel()) {
  val state = viewModel.state.value
  Scaffold(
    topBar = {
      TopAppBarMain(title = stringResource(id = R.string.favorite),
        onSearch = { navController.navigate(Screen.SearchMovieScreen.route) })
    },

    bottomBar = { BottomAppBarMain(onPopularClick = { navController.navigate(Screen.MoviesListScreen.route) }) }
  ) { padding ->

    Box(modifier = Modifier.fillMaxSize().padding(padding)) {
      LazyColumn(modifier = Modifier.fillMaxSize()) {
        state.movies?.let {
          items(it) { movie ->

            FavoriteMoviesList(
              movie = movie,
              onMovieClick = {
                navController.navigate(Screen.MovieDetailsScreen.route + "/${movie.kinopoiskId}")
              },
              onMovieLongClick = { movie ->
//                coroutineScope.launch {
//                  viewModel.getMovie(movie.kinopoiskId)
//                }
              }
            )
          }
        }
      }
    }
  }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteMoviesList(
  movie: MovieInfo,
  isFavorite: Boolean = false,
  onMovieClick: (MovieInfo) -> Unit,
  onMovieLongClick: (MovieInfo) -> Unit,
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .combinedClickable(
        onClick = {
          onMovieClick(movie)
        },
        onLongClick = {
          //onMovieLongClick(movie)
          // TODO(add fav icon )
        }
      )
      .padding(10.dp),
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    AsyncImage(
      model = ImageRequest.Builder(context = LocalContext.current)
        .data(movie.posterUrl)
        .crossfade(true)
        .build(),
      contentDescription = stringResource(id = R.string.poster),
      modifier = Modifier
        .size(160.dp)
        .padding(end = 16.dp)
        .weight(0.40f),
      contentScale = ContentScale.FillHeight,

      error = painterResource(id = R.drawable.ic_broken_image),
      placeholder = painterResource(id = R.drawable.loading_img)
    )
    Column(
      modifier = Modifier.weight(0.50f),
      verticalArrangement = Arrangement.Center
    ) {
      Text(
        text = movie.nameRu,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Left,
        maxLines = 1
      )
      val genre = movie.genres[0].genre
      val formattedGenre = genre.substring(0, 1).uppercase() + genre.substring(1).lowercase()
      Text(text = "$formattedGenre (${movie.year})", color = Color.Gray)
    }
    if (isFavorite) {
      Icon(
        imageVector = Icons.Filled.Favorite,
        contentDescription = stringResource(R.string.favorite),
        tint = colorResource(id = R.color.blue),
        modifier = Modifier
          .padding(start = 8.dp)
          .weight(0.10f)
          .align(Alignment.CenterVertically)
      )
    }
  }
}


@Preview
@Composable
fun FavoriteMoviesListScreenPreview(modifier: Modifier = Modifier) {
}