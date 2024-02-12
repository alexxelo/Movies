package com.example.movies.presentation.utils


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.movies.R


@Composable
fun BottomAppBarMain(
  modifier: Modifier = Modifier,
  onPopularClick: () -> Unit = {},
  onFavoriteClick: () -> Unit = {}
) {
  var selectedButton by remember { mutableStateOf(BottomBarButton.Popular) }

  BottomAppBar(
    modifier = modifier,
    actions = {

      BottomAppBarButton(
        modifier = modifier.weight(1f),
        text = stringResource(id = R.string.popular),
        isSelected = selectedButton == BottomBarButton.Popular,
        onClick = { onPopularClick()
          selectedButton = BottomBarButton.Popular }
      )
      BottomAppBarButton(
        modifier = modifier.weight(1f),
        text = stringResource(id = R.string.favorite),
        isSelected = selectedButton == BottomBarButton.Favorites,
        onClick = {
          onFavoriteClick()
          selectedButton = BottomBarButton.Favorites
        }
      )
    },
    containerColor = Color.Transparent
  )
}

@Composable
fun BottomAppBarButton(modifier: Modifier, text: String, isSelected: Boolean, onClick: () -> Unit) {
  Button(
    onClick = { onClick() },
    colors = ButtonDefaults.buttonColors(
      containerColor = if (isSelected) colorResource(id = R.color.blue) else colorResource(id = R.color.light_blue),
      contentColor = if (isSelected) colorResource(id = R.color.white) else colorResource(id = R.color.blue),
    ),
    modifier = modifier
      .padding(horizontal = 8.dp)
  ) {
    Text(text = text)
  }
}

enum class BottomBarButton {
  Popular,
  Favorites
}