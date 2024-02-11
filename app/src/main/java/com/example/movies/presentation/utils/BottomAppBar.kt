package com.example.movies.presentation.utils


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun BottomAppBarMain(modifier: Modifier = Modifier) {
  BottomAppBar(
    modifier= modifier,
    actions = {
      Button(onClick = { /*TODO*/ }, modifier.padding(horizontal = 8.dp).weight(1f)) {
        Text(text = "Популярные")
      }
      Button(onClick = { /*TODO*/ },modifier.padding(horizontal = 8.dp).weight(1f)) {
        Text(text = "Избранные")
      }
    }
  )
}

@Preview
@Composable
fun BottomBarPreview(modifier: Modifier = Modifier) {
  BottomAppBarMain(modifier = modifier)
}