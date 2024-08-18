package com.example.movies.presentation.movies_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.common.Resource
import com.example.movies.domain.model.MovieDetail
import com.example.movies.domain.model.MovieInfo
import com.example.movies.domain.model.toFavoriteMovie
import com.example.movies.domain.use_cases.AddMovieUseCase
import com.example.movies.domain.use_cases.DeleteFavoriteMovieUseCase
import com.example.movies.domain.use_cases.GetMoviesListUseCase
import com.example.movies.domain.use_cases.GetMoviesStreamUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
  private val getMoviesListUseCase: GetMoviesListUseCase,
  private val addMovieUseCase: AddMovieUseCase,

  private val getMoviesStreamUseCase: GetMoviesStreamUseCase,
  private val deleteFromFavoriteUseCase: DeleteFavoriteMovieUseCase,
) : ViewModel() {

  private val _allMovies = MutableStateFlow(MoviesListState())
  val allMovies: StateFlow<MoviesListState> = _allMovies.asStateFlow()

  private val _favMovies = MutableStateFlow(FavoriteMoviesState())
  val favMovies: StateFlow<FavoriteMoviesState> = _favMovies.asStateFlow()

  private val _searchText = MutableStateFlow("")
  val searchText = _searchText.asStateFlow()

  private val _originalMovies = MutableStateFlow<List<MovieDetail>>(emptyList())
  private val _fMovies = MutableStateFlow<List<MovieInfo>>(emptyList())


  init {
    getMovies()
    getFavoriteMovies()
  }

  private fun getMovies() {
    getMoviesListUseCase().onEach { result ->
      when (result) {
        is Resource.Success -> {
          _allMovies.value = MoviesListState(movies = result.data)
          _originalMovies.value = result.data?.items ?: emptyList()
        }

        is Resource.Error -> {
          _allMovies.value =
            MoviesListState(error = result.message ?: "An unexpected error occurred")
        }

        is Resource.Loading -> {
          _allMovies.value = MoviesListState(isLoading = true)
        }
      }
    }.launchIn(viewModelScope)
  }

  private fun getFavoriteMovies() {
    getMoviesStreamUseCase().onEach { result ->
      when (result) {
        is Resource.Success -> {
          _favMovies.value = FavoriteMoviesState(movies = result.data)
          _fMovies.value = result.data ?: emptyList()
        }

        is Resource.Error -> {
          _favMovies.value =
            FavoriteMoviesState(error = result.message ?: "An unexpected error occurred")
        }

        is Resource.Loading -> {
          _favMovies.value = FavoriteMoviesState(isLoading = true)
        }
      }
    }.launchIn(viewModelScope)
  }


  private suspend fun deleteMovie(movieId: Int) {
    deleteFromFavoriteUseCase(movieId)
  }

  private suspend fun addMovie(movieInfo: MovieInfo) {
    addMovieUseCase(movieInfo.toFavoriteMovie())
  }

  fun toggleFavourite(movieId: Int, movieInfo: MovieInfo) {
    viewModelScope.launch {
      val isFavorite = isFavourite(movieId)

      val currentList = _favMovies.value.movies?.toMutableList() ?: mutableListOf()
      if (isFavorite) {
        deleteMovie(movieId)
        currentList.removeAll { it.kinopoiskId == movieId }
      } else {
        addMovie(movieInfo)
        currentList.add(movieInfo)
      }
      _favMovies.value = _favMovies.value.copy(movies = currentList)
    }
  }

  fun onSearchTextChange(text: String, isFav: Boolean) {
    _searchText.value = text
    if (isFav) {
      filterFavoriteMovies(query = text)
    } else {
      filterPopularMovies(query = text)
    }
  }

  private fun filterPopularMovies(query: String) {
    val filteredMovies = if (query.isEmpty()) {
      _originalMovies.value
    } else {
      _originalMovies.value.filter { it.nameRu.contains(query, ignoreCase = true) }
    }
    _allMovies.value = _allMovies.value.copy(
      movies = _allMovies.value.movies?.copy(items = filteredMovies)
    )
  }

  private fun filterFavoriteMovies(query: String) {
    val filteredMovies = if (query.isEmpty()) {
      _fMovies.value
    } else {
      _fMovies.value.filter { it.nameRu.contains(query, ignoreCase = true) }
    }
    _favMovies.value = _favMovies.value.copy(
      movies = filteredMovies
    )
  }

  fun isFavourite(movieId: Int): Boolean {
    return _favMovies.value.movies?.any { it.kinopoiskId == movieId } ?: false
  }
}



