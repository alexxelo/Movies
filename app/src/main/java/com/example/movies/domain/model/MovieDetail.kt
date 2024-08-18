package com.example.movies.domain.model

import com.example.movies.data.source.network.dto.Countries
import com.example.movies.data.source.network.dto.Genres

data class MovieDetail(
  var kinopoiskId: Int,
  var nameRu: String,
  var nameEn: String,
  var nameOriginal: String,
  var countries: List<Countries>,
  var genres: List<Genres>,
  var ratingKinopoisk: Double,
  var ratingImdb: Double,
  var year: String,
  var type: String,
  var posterUrl: String,
  var posterUrlPreview: String,
  var description: String?
)

fun MovieDetail.toMovieInfo(): MovieInfo = MovieInfo(
  kinopoiskId = kinopoiskId,
  nameRu = nameRu,
  nameEn = nameEn,
  nameOriginal = nameOriginal,
  countries = countries,
  genres = genres,
  ratingKinopoisk = ratingKinopoisk,
  ratingImdb = ratingImdb,
  year = year,
  type = type,
  posterUrl = posterUrl,
  description = description ?: "",
  posterUrlPreview = posterUrlPreview
)