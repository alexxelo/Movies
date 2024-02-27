package com.example.movies.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movies.data.source.network.dto.Countries
import com.example.movies.data.source.network.dto.Genres
import com.example.movies.data.source.network.dto.Items
import com.example.movies.domain.model.MovieInfo

@Entity(tableName = "movies")
data class TemporaryMovie(
  @PrimaryKey(autoGenerate = true)
  val id: Int,
  val total: Int,
  val totalPages: Int,
  val items: List<Items>
)
@Entity(tableName = "favorite_movies")
data class FavoriteMovie(
  @PrimaryKey(autoGenerate = true)
  val id: Int = 0,
  val kinopoiskId: Int,
  val kinopoiskHDId: String? = "",
  val imdbId: String? = "",
  val nameRu: String? = "",
  val nameEn: String? = "",
  val nameOriginal: String? = "",
  val posterUrl: String? = "",
  val posterUrlPreview: String? = "",
  val coverUrl: String? = "",
  val logoUrl: String? = "",
  val reviewsCount: Int,
  val ratingGoodReview: Double,
  val ratingGoodReviewVoteCount: Int,
  val ratingKinopoisk: Double,
  val ratingKinopoiskVoteCount: Int,
  val ratingImdb: Double,
  val ratingImdbVoteCount: Int,
  val ratingFilmCritics: Double,
  val ratingFilmCriticsVoteCount: Int,
  val ratingAwait: Double,
  val ratingAwaitCount: Int,
  val ratingRfCritics: Double,
  val ratingRfCriticsVoteCount: Int,
  val webUrl: String? = "",
  val year: Int,
  val filmLength: Int,
  val slogan: String? = "",
  val description: String? = "",
  val shortDescription: String? = "",
  val editorAnnotation: String? = "",
  val isTicketsAvailable: Boolean,
  val productionStatus: String? = "",
  val type: String? = "",
  val ratingMpaa: String? = "",
  val ratingAgeLimits: String? = "",
  val hasImax: Boolean,
  val has3D: Boolean,
  val lastSync: String? = "",
  val countries: List<Countries>,
  val genres: List<Genres>,
  val startYear: Int,
  val endYear: Int,
  val serial: Boolean,
  val shortFilm: Boolean,
  val completed: Boolean
)
fun FavoriteMovie.toMovieInfo(): MovieInfo = MovieInfo(
  kinopoiskId = kinopoiskId,
  kinopoiskHDId = kinopoiskHDId ?: "",
  imdbId = imdbId ?: "",
  nameRu = nameRu ?: "", nameEn = nameEn ?: "",
  nameOriginal = nameOriginal ?: "",
  posterUrl = posterUrl ?: "",
  posterUrlPreview = posterUrlPreview ?: "",
  coverUrl = coverUrl ?: "",
  logoUrl = logoUrl ?: "",
  reviewsCount = reviewsCount,
  ratingGoodReview = ratingGoodReview,
  ratingGoodReviewVoteCount = ratingGoodReviewVoteCount,
  ratingKinopoisk = ratingKinopoisk,
  ratingKinopoiskVoteCount = ratingKinopoiskVoteCount,
  ratingImdb = ratingImdb,
  ratingImdbVoteCount = ratingImdbVoteCount,
  ratingFilmCritics = ratingFilmCritics,
  ratingFilmCriticsVoteCount = ratingFilmCriticsVoteCount,
  ratingAwait = ratingAwait,
  ratingAwaitCount = ratingAwaitCount,
  ratingRfCritics = ratingRfCritics,
  ratingRfCriticsVoteCount = ratingRfCriticsVoteCount,
  webUrl = webUrl ?: "",
  year = year,
  filmLength = filmLength,
  slogan = slogan ?: "",
  description = description ?: "",
  shortDescription = shortDescription ?: "",
  editorAnnotation = editorAnnotation ?: "",
  isTicketsAvailable = isTicketsAvailable,
  productionStatus = productionStatus ?: "",
  type = type ?: "",
  ratingMpaa = ratingMpaa ?: "",
  ratingAgeLimits = ratingAgeLimits ?: "",
  hasImax = hasImax,
  has3D = has3D,
  lastSync = lastSync ?: "",
  countries = countries,
  genres = genres,
  startYear = startYear,
  endYear = endYear,
  serial = serial,
  shortFilm = shortFilm,
  completed = completed
)