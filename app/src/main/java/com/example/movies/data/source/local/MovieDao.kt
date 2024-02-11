package com.example.movies.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TemporaryMovieDao {
  @Query("SELECT * FROM movies")
  fun getAll(): Flow<List<TemporaryMovie>>

  @Query("SELECT * FROM movies WHERE id = :id")
  fun getMovie(id: Int): Flow<TemporaryMovie>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(movie: TemporaryMovie)
}

@Dao
interface FavoriteMovieDao {
  @Query("SELECT * FROM favorite_movies")
  fun getAll(): Flow<List<FavoriteMovie>>

  @Query("SELECT * FROM favorite_movies WHERE id = :id")
  fun getMovie(id: Int): Flow<FavoriteMovie>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(movie: FavoriteMovie)

  @Delete
  suspend fun delete(movie: FavoriteMovie)
}