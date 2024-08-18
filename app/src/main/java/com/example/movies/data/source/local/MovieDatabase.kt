package com.example.movies.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movies.data.source.local.converter.Converters

@Database(entities = [FavoriteMovie::class], version = 4, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {
  abstract fun favoriteMoviesDao(): FavoriteMovieDao

}