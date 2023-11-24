package com.example.kotlinkingdomking.movieAPI

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

object MovieAPI {

    const val API_SEARCH_URL = "https://api.themoviedb.org/3/search/tv?api_key=17117ab9c18276d48d8634390c025df4&language=en-US&query="
    const val API_SEARCH_PARAMS = "&page=1&include_adult=false"

    const val API_DETAILS_URL = "https://api.themoviedb.org/3/tv/"
    const val API_DETAILS_PARAMS = "?api_key=17117ab9c18276d48d8634390c025df4&language=en-US"

    const val API_SMALL_IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    const val API_BIG_IMAGE_URL = "https://image.tmdb.org/t/p/w1280"

    val gson = Gson()
    val client = OkHttpClient()

    fun searchMovies(query: String): List<MovieBean> {
        if (query.length < 3) {
            println("Il faut au moins 3 caractères")
            throw Exception("Il faut au moins 3 caractères")
        }

        val json: String = sendGet(API_SEARCH_URL + query + API_SEARCH_PARAMS)
        val data : MovieSearchResults = gson.fromJson(json, MovieSearchResults::class.java)

        return data.results
    }

    fun getMovieDetails(id: Int): MovieDetailsBean {
        val json: String = sendGet(API_DETAILS_URL + id + API_DETAILS_PARAMS)

        return gson.fromJson(json, MovieDetailsBean::class.java)
    }

    fun sendGet(url: String): String {
        println("url : $url")
        val request = Request.Builder().url(url).build()
        return client.newCall(request).execute().use {
            if (!it.isSuccessful) {
                throw Exception("Erreur :${it.code}")
            }
            it.body.string()
        }
    }
}