package com.example.movies.presentation.utils


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movies.R


@Composable
fun BottomAppBarMain(modifier: Modifier = Modifier, isSelected: Boolean = true) {
  BottomAppBar(
    modifier = modifier,
    actions = {

      BottomAppBarButton(
        modifier = modifier.weight(1f),
        text = stringResource(id = R.string.popular),
        isSelected = false,
        onClick = { }
      )
      BottomAppBarButton(
        modifier = modifier.weight(1f),
        text = stringResource(id = R.string.favorite),
        isSelected = isSelected,
        onClick = { }
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

@Preview
@Composable
fun BottomBarPreview(modifier: Modifier = Modifier) {
  BottomAppBarMain(modifier = modifier)
}