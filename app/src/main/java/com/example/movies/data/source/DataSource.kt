package com.example.movies.data.source

import kotlinx.coroutines.flow.Flow

interface DataSource<T> {
  fun getAll(): Flow<List<T>>
  fun getMovie(id:Int): Flow<T>
  suspend fun insert(movie :T)
  suspend fun delete(movie :T)
}