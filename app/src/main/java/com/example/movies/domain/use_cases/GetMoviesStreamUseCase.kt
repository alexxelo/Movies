package com.example.movies.domain.use_cases

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.movies.common.Resource
import com.example.movies.data.source.local.toMovieInfo
import com.example.movies.domain.model.MovieInfo
import com.example.movies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMoviesStreamUseCase @Inject constructor(private val repository: MovieRepository) {
  operator fun invoke() : Flow<Resource<List<MovieInfo>>> = flow {
    try {
      emit(Resource.Loading<List<MovieInfo>>())
      val movieList = mutableListOf<MovieInfo>()
      repository.getMoviesStream().collect { favoriteMovieList ->
        movieList.addAll(favoriteMovieList.map { it.toMovieInfo() })
      }
      Log.d("Debug"," moviesDto = $movieList")
      emit(Resource.Success<List<MovieInfo>>(movieList))
    } catch (e: HttpException){
      emit(Resource.Error<List<MovieInfo>>(e.localizedMessage ?: "An unexpected error is occurred"))
    } catch (e: IOException){
      emit(Resource.Error<List<MovieInfo>>(e.localizedMessage ?: "Couldn't reach. "))

    }
  }
}