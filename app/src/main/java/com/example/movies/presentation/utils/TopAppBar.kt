package com.example.movies.presentation.utils


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.movies.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarMain(title: String, onSearch: () -> Unit) {
  TopAppBar(
    title = {
      Text(text = title, fontWeight = FontWeight.Bold)
    },
    actions = {
      IconButton(onClick = onSearch) {
        Icon(
          imageVector = Icons.Filled.Search,
          contentDescription = stringResource(id = R.string.search),
          tint = colorResource(id = R.color.blue)
        )
      }
    }

  )
}
@Preview
@Composable
fun TopAppBarPreview(modifier: Modifier = Modifier) {
  //TopAppBarMain()
}