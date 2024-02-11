package com.example.movies.domain.repository

import com.example.movies.data.source.network.dto.Items
import com.example.movies.data.source.network.dto.MoviesDto
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

  //room
  fun getMoviesStream(): Flow<List<MoviesDto>>
  fun getMovieStream(movieId: Int): Flow<Items>
  suspend fun insert(items: Items)
  suspend fun delete(items: Items)

  // retrofit
  suspend fun getMovies():List<MoviesDto>
  suspend fun getMovieById(id:String):Items

}