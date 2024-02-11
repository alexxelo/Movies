package com.example.movies.domain.model

data class Movies(
  var total: Int,
  var totalPages: Int,
  var items: List<MovieDetail>
)

