package com.example.kotlinkingdomking

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinkingdomking.movieAPI.MovieAPI
import com.example.kotlinkingdomking.movieAPI.MovieBean
import com.example.kotlinkingdomking.movieAPI.MovieDetailsBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private var _movies = mutableStateListOf<MovieBean>()
    val moviesList  : List<MovieBean> = _movies

    var movieDetails = mutableStateOf<MovieDetailsBean?>(null)
        private set

    var searchText by mutableStateOf("")
        private set

    var isLoadInProgress by mutableStateOf(false)
        private set

    fun updateSearchText(newText : String){
        searchText = newText
    }

    fun searchMovies() {
        _movies.clear()
        isLoadInProgress = true

        viewModelScope.launch(Dispatchers.Default) {
            try {
                val list: List<MovieBean> = MovieAPI.searchMovies(searchText)
                _movies.addAll(list)
            }
            catch(e:Exception){
                e.printStackTrace()
            }
            isLoadInProgress = false
        }

    }

    fun loadMovieDetails(id: Int) {
        movieDetails.value = null
        isLoadInProgress = true

        viewModelScope.launch(Dispatchers.Default) {
            try {
                val movie: MovieDetailsBean = MovieAPI.getMovieDetails(id)
                movieDetails.value = movie
            }
            catch(e:Exception){
                e.printStackTrace()
            }
            isLoadInProgress = false
        }

    }
}