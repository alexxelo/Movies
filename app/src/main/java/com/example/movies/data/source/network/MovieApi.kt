package com.example.movies.data.source.network

import com.example.movies.data.source.network.dto.Items
import com.example.movies.data.source.network.dto.MoviesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
  @GET("/api/v2.2/films/collections")
  suspend fun getTopMovies(
    @Query("type") type: String = "TOP_POPULAR_ALL",
  ): Response<MoviesDto>

  @GET("/api/v2.2/films/{movieId}")
  suspend fun getMovieById(
    @Path("movieId") movieId: Int
  ): Response<Items>
}