package com.example.movies.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movies.data.source.network.dto.Countries
import com.example.movies.data.source.network.dto.Genres
import com.example.movies.domain.model.MovieInfo

@Entity(tableName = "favorite_movies")
data class FavoriteMovie(
  @PrimaryKey
  var kinopoiskId: Int,
  var nameRu: String? = "",
  var nameEn: String? = "",
  var nameOriginal: String? = "",
  var countries: List<Countries> = emptyList(),
  var genres: List<Genres> = emptyList(),
  var ratingKinopoisk: Double = 0.0,
  var ratingImdb: Double = 0.0,
  var year: String? = "",
  var type: String? = "",
  var posterUrl: String? = "",
  var posterUrlPreview: String? = "",
  val description: String? = "",
)

fun FavoriteMovie.toMovieInfo(): MovieInfo = MovieInfo(
  kinopoiskId = kinopoiskId,
  nameRu = nameRu ?: "",
  nameEn = nameEn ?: "",
  nameOriginal = nameOriginal ?: "",
  posterUrl = posterUrl ?: "",
  posterUrlPreview = posterUrlPreview ?: "",
  ratingKinopoisk = ratingKinopoisk,
  ratingImdb = ratingImdb,
  year = year ?: "",
  description = description ?: "",
  type = type ?: "",
  countries = countries,
  genres = genres,
)