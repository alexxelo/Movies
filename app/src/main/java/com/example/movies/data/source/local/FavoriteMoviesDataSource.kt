package com.example.movies.data.source.local

import com.example.movies.data.source.DataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteMoviesDataSource @Inject constructor(private val favoriteMovieDao: FavoriteMovieDao) : DataSource<FavoriteMovie> {
  fun getMovie(id: Int): Flow<FavoriteMovie> {
    return favoriteMovieDao.getMovie(id = id)
  }

  override fun getAll(): Flow<List<FavoriteMovie>> {
    return favoriteMovieDao.getAll()
  }

  override suspend fun insert(movie: FavoriteMovie) {
    favoriteMovieDao.insert(movie)
  }
}