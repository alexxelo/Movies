package com.example.movies.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movies.data.source.local.converter.Converters

@Database(entities = [TemporaryMovie::class,  FavoriteMovie::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {

  abstract fun temporaryMoviesDao(): TemporaryMovieDao
  abstract fun favoriteMoviesDao(): FavoriteMovieDao

}