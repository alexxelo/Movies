package com.example.movies.presentation.movie_details.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movies.domain.model.MovieInfo

@Composable
fun MovieDetailsInfo(
  movie: MovieInfo
) {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(20.dp),
    horizontalAlignment = Alignment.Start,
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    Text(
      text = movie.nameRu,
      fontWeight = FontWeight.Bold,
      fontSize = 30.sp,
      textAlign = TextAlign.Start,
      modifier = Modifier
    )
    Text(
      text = movie.description, textAlign = TextAlign.Start, color = Color.Gray
    )
    Text(
      text = "Жанры: "+movie.genres[0].genre, textAlign = TextAlign.Start, color = Color.Gray
    )
    Text(
      text = "Страны: "+movie.countries[0].country, textAlign = TextAlign.Start, color = Color.Gray
    )

  }
}
