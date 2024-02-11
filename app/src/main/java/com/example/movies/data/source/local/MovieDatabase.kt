package com.example.movies.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movies.data.source.local.converter.Converters

@Database(entities = [TemporaryMovie::class,  FavoriteMovie::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {

  abstract fun temporaryDataDao(): TemporaryMovieDao
  abstract fun favoriteMovieDao(): FavoriteMovieDao
  companion object {
    @Volatile
    var Instance: MovieDatabase? = null

    fun getDatabase(context: Context): MovieDatabase {

      return Instance ?: synchronized(this) {
        Room.databaseBuilder(context = context, MovieDatabase::class.java, "movies_database")
          .fallbackToDestructiveMigration()
          .build()
          .also { Instance = it }
      }
    }
  }
}