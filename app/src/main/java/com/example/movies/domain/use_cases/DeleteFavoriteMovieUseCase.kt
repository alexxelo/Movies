package com.example.movies.domain.use_cases

import com.example.movies.domain.repository.MovieRepository
import javax.inject.Inject

class DeleteFavoriteMovieUseCase @Inject constructor(private val repository: MovieRepository) {
  suspend operator fun invoke(movieId: Int) {
    repository.delete(movieId)
  }
}