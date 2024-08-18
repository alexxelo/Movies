package com.example.movies.presentation.utils


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movies.R
import com.example.movies.presentation.navigation.Screen

enum class BottomBarButton {
  Popular,
  Favorites
}

@Composable
fun BottomAppBarMain(
  modifier: Modifier = Modifier,
  selectedButton: BottomBarButton,
  onButtonSelected: (BottomBarButton) -> Unit,
  onPopularClick: () -> Unit = {},
  onFavoriteClick: () -> Unit = {}
) {
  BottomAppBarButton(
    modifier = modifier,
    text = stringResource(id = R.string.popular),
    isSelected = selectedButton == BottomBarButton.Popular,
    onClick = {
      onPopularClick()
      onButtonSelected(BottomBarButton.Popular)
    }
  )
  BottomAppBarButton(
    modifier = modifier,
    text = stringResource(id = R.string.favorite),
    isSelected = selectedButton == BottomBarButton.Favorites,
    onClick = {
      onFavoriteClick()
      onButtonSelected(BottomBarButton.Favorites)
    }
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


@Composable
fun EmptyScreen(modifier: Modifier = Modifier, text: String) {
  Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Text(text = text, textAlign = TextAlign.Center)
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarMain(
  text: String,
  isSearching: Boolean,
  searchText: String,
  onSearchToggle: () -> Unit,
  onSearchTextChange: (String) -> Unit,
  onClearSearch: () -> Unit
) {
  if (isSearching) {
    TextField(
      value = searchText,
      onValueChange = { query ->
        onSearchTextChange(query)
      },
      placeholder = { Text(text = stringResource(id = R.string.search)) },
      singleLine = true,
      colors = TextFieldDefaults.textFieldColors(
        containerColor = Color.Transparent,
      ),
      trailingIcon = {
        IconButton(onClick = {
          onClearSearch()
        }) {
          Icon(Icons.Default.Clear, contentDescription = "Clear")
        }
      },
      modifier = Modifier.fillMaxWidth()
    )
  } else {
    Text(
      text = text,
      fontWeight = FontWeight.Bold
    )
  }
}

@Composable
fun ShowError(
  navController: NavController,
  error: String
) {
  Column(
    modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Image(
      painter = painterResource(id = R.drawable.error),
      contentDescription = stringResource(id = R.string.error_image)
    )
    Text(
      text = error,
      color = MaterialTheme.colorScheme.error,
      textAlign = TextAlign.Center,
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 20.dp)
    )
    Button(
      onClick = {
        navController.navigate(Screen.MoviesListScreen.route) {
          popUpTo(Screen.MoviesListScreen.route) {
            inclusive = true
          }
        }
      },
      colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.blue))
    ) {
      Text(text = stringResource(id = R.string.reload))
    }
  }
}