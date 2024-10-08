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
      repository.getMovieStream(movieId).collect { favoriteMovie ->
        if (favoriteMovie != null) {
          emit(Resource.Success(favoriteMovie.toMovieInfo()))
        } else {
          emit(Resource.Error<MovieInfo>("Movie not found in local database"))
        }
      }
    } catch (e: HttpException) {
      emit(Resource.Error<MovieInfo>(e.localizedMessage ?: "An unexpected error is occurred"))
    } catch (e: IOException) {
      emit(Resource.Error<MovieInfo>(e.localizedMessage ?: "Couldn't reach. "))

    }

  }
}