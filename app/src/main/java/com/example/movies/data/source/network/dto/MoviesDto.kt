package com.example.movies.data.source.network.dto

import com.example.movies.domain.model.Movies

//
//data class Movie(
//  var kinopoiskId: Int,
//  var kinopoiskHDId: String,
//  var imdbId: String,
//  var nameRu: String,
//  var nameEn: String,
//  var nameOriginal: String,
//  var posterUrl: String,
//  var posterUrlPreview: String,
//  var coverUrl: String,
//  var logoUrl: String,
//  var reviewsCount: Int,
//  var ratingGoodReview: Double,
//  var ratingGoodReviewVoteCount: Int,
//  var ratingKinopoisk: Double,
//  var ratingKinopoiskVoteCount: Int,
//  var ratingImdb: Double,
//  var ratingImdbVoteCount: Int,
//  var ratingFilmCritics: Double,
//  var ratingFilmCriticsVoteCount: Int,
//  var ratingAwait: Double,
//  var ratingAwaitCount: Int,
//  var ratingRfCritics: Double,
//  var ratingRfCriticsVoteCount: Int,
//  var webUrl: String,
//  var year: Int,
//  var filmLength: Int,
//  var slogan: String,
//  var description: String,
//  var shortDescription: String,
//  var editorAnnotation: String,
//  var isTicketsAvailable: Boolean,
//  var productionStatus: String,
//  var type: String,
//  var ratingMpaa: String,
//  var ratingAgeLimits: String,
//  var hasImax: Boolean,
//  var has3D: Boolean,
//  var lastSync: String,
//  var countries: List<Countries>,
//  var genres: List<Genres>,
//  var startYear: Int,
//  var endYear: Int,
//  var serial: Boolean,
//  var shortFilm: Boolean,
//  var completed: Boolean
//
//)

data class MoviesDto(
  var total: Int,
  var totalPages: Int,
  var items: List<Items>

)

fun MoviesDto.toMovies():Movies{
  return Movies(
    total,
    totalPages,
    items
  )
}
