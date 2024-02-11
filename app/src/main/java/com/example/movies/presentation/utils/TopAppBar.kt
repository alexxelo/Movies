package com.example.movies.presentation.utils


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarMain(title: String, onSearch: () -> Unit) {
  TopAppBar(
    title = {
      Text(text = title)
    },
    actions = {
      IconButton(onClick = onSearch) {
        Icon(imageVector = Icons.Filled.Search, contentDescription = "")
      }
    }
  )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppSearch(title: String, onBackh: () -> Unit) {
  TopAppBar(
    title = {
      Text(text = title)
    },
    navigationIcon = {
      IconButton(onClick = onBackh) {
        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
      }
    }
  )
}


@Preview
@Composable
fun TopAppBarPreview(modifier: Modifier = Modifier) {
  //TopAppBarMain()
}