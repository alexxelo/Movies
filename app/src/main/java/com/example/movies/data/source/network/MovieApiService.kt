package com.example.movies.data.source.network

import com.example.movies.data.source.network.dto.Items
import com.example.movies.data.source.network.dto.MoviesDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {
  @GET("/api/v2.2/films/top")
  suspend fun getTopMovies(
    @Query("type") type: String = "TOP_100_POPULAR_FILMS",
  ): List<MoviesDto>

  @GET(" /api/v2.2/films/top/{movieId}")
  suspend fun getMovieById(
    @Path("movieId") movieId: String
  ): Items
}