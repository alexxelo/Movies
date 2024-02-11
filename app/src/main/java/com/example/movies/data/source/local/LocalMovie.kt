package com.example.movies.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movies.data.source.network.dto.Countries
import com.example.movies.data.source.network.dto.Genres
import com.example.movies.data.source.network.dto.Items

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
  @PrimaryKey
  val id: Int,
  val kinopoiskId: Int,
  val nameRu: String,
  val nameEn: String,
  val nameOriginal: String,
  val countries: List<Countries>,
  val genres: List<Genres>,
  val ratingKinopoisk: Double,
  val ratingImbd: Double,
  val year: String,
  val type: String,
  val posterUrl: String,
  val posterUrlPreview: String,
)