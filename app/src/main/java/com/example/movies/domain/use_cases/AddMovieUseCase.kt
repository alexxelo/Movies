package com.example.movies.domain.use_cases

import com.example.movies.data.source.local.FavoriteMovie
import com.example.movies.domain.repository.MovieRepository
import javax.inject.Inject

class AddMovieUseCase @Inject constructor(private val repository: MovieRepository) {
  suspend operator fun invoke(movie:FavoriteMovie){
    repository.insert(movie)
  }
}