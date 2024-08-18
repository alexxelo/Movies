package com.example.movies.domain.use_cases

import com.example.movies.common.Resource
import com.example.movies.data.source.network.dto.toMovieInfo
import com.example.movies.domain.model.MovieInfo
import com.example.movies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(private val repository: MovieRepository) {

  operator fun invoke(id: Int): Flow<Resource<MovieInfo>> = flow {
    try {
      emit(Resource.Loading<MovieInfo>())
      val response = repository.getMovieById(id)
      val movie = response.body()?.toMovieInfo()
      if (movie != null) {
        emit(Resource.Success<MovieInfo>(movie))
      } else {
        emit(Resource.Error<MovieInfo>("Movie not found or invalid data"))
      }
    } catch (e: HttpException) {
      emit(Resource.Error<MovieInfo>(e.localizedMessage ?: "An unexpected error is occured"))
    } catch (e: IOException) {
      emit(
        Resource.Error<MovieInfo>(
          e.localizedMessage ?: "Couldn't reach server. Check your internet connection"
        )
      )

    }
  }
}