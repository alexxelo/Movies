package com.example.movies.domain.model

import com.example.movies.data.source.network.dto.Items

data class Movies(
  var total: Int,
  var totalPages: Int,
  var items: List<Items>
)

