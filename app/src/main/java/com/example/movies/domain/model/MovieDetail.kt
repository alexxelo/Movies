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
  var ratingImbd: Double,
  var year: String,
  var type: String,
  var posterUrl: String,
  var posterUrlPreview: String
)
