package com.example.movies.data.source.local

import com.example.movies.data.source.DataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TemporaryMoviesDataSource @Inject constructor(private val temporaryMovieDao: TemporaryMovieDao) : DataSource<TemporaryMovie> {

  fun getMovie(id: Int): Flow<TemporaryMovie> {
    return temporaryMovieDao.getMovie(id = id)
  }

  override fun getAll(): Flow<List<TemporaryMovie>> {
    return temporaryMovieDao.getAll()
  }

  override suspend fun insert(movie: TemporaryMovie) {
    return temporaryMovieDao.insert(movie)
  }


}