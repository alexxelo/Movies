package com.example.movies.data.source.local.converter

import androidx.room.TypeConverter
import com.example.movies.data.source.network.dto.Countries
import com.example.movies.data.source.network.dto.Genres
import com.example.movies.data.source.network.dto.Items
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
class Converters {

  @TypeConverter
  fun fromItemsList(items: List<Items>): String {
    return Gson().toJson(items)
  }

  @TypeConverter
  fun fromStringToItems(itemsString: String): List<Items> {
    val type = object : TypeToken<List<Items>>() {}.type
    return Gson().fromJson(itemsString, type)
  }

  @TypeConverter
  fun countriesToString(countries: List<Countries>): String {
    return Gson().toJson(countries)
  }

  @TypeConverter
  fun stringToCountries(countriesString: String): List<Countries> {
    val type = object : TypeToken<List<Countries>>() {}.type
    return Gson().fromJson(countriesString, type)
  }

  @TypeConverter
  fun genresToString(genres: List<Genres>): String {
    return Gson().toJson(genres)
  }

  @TypeConverter
  fun stringToGenres(genresString: String): List<Genres> {
    val type = object : TypeToken<List<Genres>>() {}.type
    return Gson().fromJson(genresString, type)
  }
}
