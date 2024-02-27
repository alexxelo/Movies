package com.example.movies.data.source.network.dto

import com.example.movies.data.source.local.FavoriteMovie
import com.example.movies.domain.model.MovieDetail

data class Items(
  var kinopoiskId: Int,
  var nameRu: String,
  var nameEn: String? = "",
  var nameOriginal: String? = "",
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


fun Items.toMovieDetails(): MovieDetail {
  return MovieDetail(
    kinopoiskId = kinopoiskId,
    nameRu = nameRu,
    nameEn = nameEn ?: "",
    nameOriginal = nameOriginal ?: "",
    countries = countries,
    genres = genres,
    ratingKinopoisk = ratingKinopoisk,
    ratingImbd = ratingImbd,
    year = year,
    type = type,
    posterUrl = posterUrl,
    posterUrlPreview = posterUrlPreview
  )
}

//
//fun Items.toFavoriteMovie(): FavoriteMovie {
//  return FavoriteMovie(
//    kinopoiskId = kinopoiskId,
//    nameRu = nameRu,
//    nameEn = nameEn ?: "",
//    nameOriginal = nameOriginal ?: "",
//    countries = countries,
//    genres = genres,
//    ratingKinopoisk = ratingKinopoisk,
//    ratingImbd = ratingImbd,
//    year = year,
//    type = type,
//    posterUrl = posterUrl,
//    posterUrlPreview = posterUrlPreview
//  )
//}