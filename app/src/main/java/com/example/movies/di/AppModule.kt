package com.example.movies.di

import android.content.Context
import androidx.room.Room
import com.example.movies.common.Constants
import com.example.movies.data.repository.MovieRepositoryImpl
import com.example.movies.data.source.local.FavoriteMoviesDataSource
import com.example.movies.data.source.local.MovieDatabase
import com.example.movies.data.source.local.TemporaryMoviesDataSource
import com.example.movies.data.source.network.MovieApi
import com.example.movies.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
  @Provides
  @Singleton
  fun provideAppDatabase(@ApplicationContext context: Context): MovieDatabase {
    return Room.databaseBuilder(
      context,
      MovieDatabase::class.java, "movies_database"
    ).build()
  }

  @Provides
  @Singleton
  fun provideFavoriteMoviesDataSource(database: MovieDatabase): FavoriteMoviesDataSource {
    return FavoriteMoviesDataSource(database.favoriteMoviesDao())
  }

  @Provides
  @Singleton
  fun provideTemporaryMoviesDataSource(database: MovieDatabase): TemporaryMoviesDataSource {
    return TemporaryMoviesDataSource(database.temporaryMoviesDao())
  }
}

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
  @Provides
  @Singleton
  fun provideMovieApi(): MovieApi {
    val client = OkHttpClient.Builder()
      .addInterceptor { chain ->
        val request = chain.request().newBuilder()
          .addHeader("X-API-KEY", Constants.API_KEY)
          .build()
        chain.proceed(request)
      }
      .build()
    return Retrofit.Builder()
      .client(client)
      .baseUrl(Constants.BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(MovieApi::class.java)
  }
}

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
  @Provides
  @Singleton
  fun provideMovieRepository(
    api: MovieApi,
    favoriteMovieDao: FavoriteMoviesDataSource,
    temporaryMovieDao: TemporaryMoviesDataSource
  ): MovieRepository {
    return MovieRepositoryImpl(api, temporaryMovieDao, favoriteMovieDao)
  }
}