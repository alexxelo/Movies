package com.example.movies.data.source.network.dto

import com.example.movies.domain.model.MovieDetail

data class Items(
  var kinopoiskId: Int,
  var nameRu: String? = "",
  var nameEn: String? = "",
  var nameOriginal: String? = "",
  var countries: List<Countries>,
  var genres: List<Genres>,
  var ratingKinopoisk: Double,
  var ratingImdb: Double,
  var year: String? = "",
  var type: String,
  var posterUrl: String,
  var posterUrlPreview: String,
  var description: String = ""

)

data class Countries(
  var country: String
)

data class Genres(
  var genre: String
)


fun Items.toMovieDetails(): MovieDetail = MovieDetail(
  kinopoiskId = kinopoiskId,
  nameRu = nameRu ?: "",
  nameEn = nameEn ?: "",
  nameOriginal = nameOriginal ?: "",
  countries = countries,
  genres = genres,
  ratingKinopoisk = ratingKinopoisk,
  ratingImdb = ratingImdb,
  year = year ?: "",
  type = type,
  posterUrl = posterUrl,
  description = description,
  posterUrlPreview = posterUrlPreview
)
