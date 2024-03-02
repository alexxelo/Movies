package com.example.movies.data.source.local

import com.example.movies.data.source.DataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TemporaryMoviesDataSource @Inject constructor(private val temporaryMovieDao: TemporaryMovieDao) : DataSource<TemporaryMovie> {



  override fun getAll(): Flow<List<TemporaryMovie>> {
    return temporaryMovieDao.getAll()
  }

  override fun getMovie(id: Int): Flow<TemporaryMovie> {
    TODO("Not yet implemented")
  }

  override suspend fun delete(movieId: Int) {
    TODO("Not yet implemented")
  }

  override suspend fun insert(movie: TemporaryMovie) {
    return temporaryMovieDao.insert(movie)
  }


}