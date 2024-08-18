package com.example.movies.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMovieDao {
  @Query("SELECT * FROM favorite_movies")
  fun getAll(): Flow<List<FavoriteMovie>>

  @Query("SELECT * FROM favorite_movies WHERE kinopoiskId = :id")
  fun getMovie(id: Int): Flow<FavoriteMovie>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun insert(movie: FavoriteMovie)

  @Query("DELETE FROM favorite_movies WHERE kinopoiskId = :movieId")
  suspend fun delete(movieId: Int)
}