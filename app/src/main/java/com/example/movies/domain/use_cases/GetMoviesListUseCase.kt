package com.example.movies.domain.use_cases

import com.example.movies.common.Resource
import com.example.movies.data.source.network.dto.toMovies
import com.example.movies.domain.model.Movies
import com.example.movies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMoviesListUseCase @Inject constructor(private val repository: MovieRepository) {

  operator fun invoke(): Flow<Resource<Movies>> = flow {
    try {
      emit(Resource.Loading<Movies>())
      val movies = repository.getMovies().body()!!.toMovies()
      //Log.d("Debug"," moviesDto = $movies")
      emit(Resource.Success<Movies>(movies))

    } catch (e:HttpException){
      emit(Resource.Error<Movies>(e.localizedMessage ?: "An unexpected error is occurred"))
    } catch (e:IOException){
      emit(Resource.Error<Movies>(e.localizedMessage ?: "Couldn't reach server. Check your internet connection"))

    }
  }
}