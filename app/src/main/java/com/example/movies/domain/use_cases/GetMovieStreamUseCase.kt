package com.example.movies.domain.use_cases

import com.example.movies.common.Resource
import com.example.movies.data.source.local.toMovieInfo
import com.example.movies.domain.model.MovieInfo
import com.example.movies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieStreamUseCase @Inject constructor(private val repository: MovieRepository) {

  operator fun invoke(movieId: Int): Flow<Resource<MovieInfo>> = flow {
    try {
      emit(Resource.Loading<MovieInfo>())

//      val movie = repository.getMovieStream(movieId).single()
//      val movieInfo = movie.toMovieInfo()
//      emit(Resource.Success(movieInfo))

      repository.getMovieStream(movieId).collect{
        emit(Resource.Success(it.toMovieInfo()))
      }

    } catch (e: HttpException) {
      emit(Resource.Error<MovieInfo>(e.localizedMessage ?: "An unexpected error is occurred"))
    } catch (e: IOException) {
      emit(Resource.Error<MovieInfo>(e.localizedMessage ?: "Couldn't reach. "))

    }

  }
}