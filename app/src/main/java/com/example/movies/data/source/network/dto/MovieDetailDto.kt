package com.example.movies.data.source.network.dto

import com.example.movies.domain.model.MovieDetail

data class Items(
  var kinopoiskId: Int,
  var nameRu: String,
  var nameEn: String,
  var nameOriginal: String,
  var countries: List<Countries>,
  var genres: List<Genres>,
  var ratingKinopoisk: Double,
  var ratingImbd: Double,
  var year: String,
  var type: String,
  var posterUrl: String,
  var posterUrlPreview: String

)

data class Countries(
  var country: String
)

data class Genres(
  var genre: String
)


fun Items.toMovie(): MovieDetail {
  return MovieDetail(
    kinopoiskId,
    nameRu,
    nameEn,
    nameOriginal,
    countries, genres,
    ratingKinopoisk,
    ratingImbd,
    year,
    type,
    posterUrl,
    posterUrlPreview
  )
}