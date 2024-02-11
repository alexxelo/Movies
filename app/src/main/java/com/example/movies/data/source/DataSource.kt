package com.example.movies.data.source

import kotlinx.coroutines.flow.Flow

interface DataSource<T> {
  fun getAll(): Flow<List<T>>
  suspend fun insert(movie :T)
}