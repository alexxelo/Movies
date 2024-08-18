package com.example.movies.domain.model

import com.example.movies.data.source.local.FavoriteMovie
import com.example.movies.data.source.network.dto.Countries
import com.example.movies.data.source.network.dto.Genres

data class MovieInfo(
  var kinopoiskId: Int,
  var nameRu: String,
  var nameEn: String = "",
  var nameOriginal: String = "",
  var countries: List<Countries> = emptyList(),
  var genres: List<Genres> = emptyList(),
  var ratingKinopoisk: Double,
  var ratingImdb: Double,
  var year: String = "",
  var type: String = "",
  var posterUrl: String = "",
  var posterUrlPreview: String = "",
  var description: String ,
)

fun MovieInfo.toFavoriteMovie(): FavoriteMovie = FavoriteMovie(
  kinopoiskId = kinopoiskId,
  nameRu = nameRu,
  nameEn = nameEn,
  nameOriginal = nameOriginal,
  posterUrl = posterUrl,
  posterUrlPreview = posterUrlPreview ,
  ratingKinopoisk = ratingKinopoisk,
  ratingImdb = ratingImdb,
  year = year,
  description = description,
  type = type,
  countries = countries,
  genres = genres
)
