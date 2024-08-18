package com.example.movies.presentation.movie_details.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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

@Composable
fun MovieDetailsInfo(
  movieDescription: String,
  movieName: String,
  movieGenre: String,
  movieCountry: String
) {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(20.dp),
    horizontalAlignment = Alignment.Start,
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    Text(
      text = movieName,
      fontWeight = FontWeight.Bold,
      fontSize = 30.sp,
      textAlign = TextAlign.Start,
      modifier = Modifier
    )
    Text(
      text = movieDescription, textAlign = TextAlign.Start, color = Color.Gray
    )
    Info(title = "Жанр: ", text = movieGenre)
    Info(title = "Страна: ", text = movieCountry)
  }
}

@Composable
fun Info(title: String, text: String) {
  Row(modifier = Modifier.fillMaxWidth()) {
    Text(
      text = title,
      fontWeight = FontWeight.Bold,
      textAlign = TextAlign.Start,
      color = Color.DarkGray
    )
    Text(
      text = text, textAlign = TextAlign.Start, color = Color.Gray
    )
  }
}
