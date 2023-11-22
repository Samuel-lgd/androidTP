package com.example.kotlinkingdomking.weatherAPI

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

object WeatherAPI {

    var API_URL:String = "https://api.openweathermap.org/data/2.5/weather?q="
    var API_PARAMS:String = "&appid=b80967f0a6bd10d23e44848547b26550&units=metric&lang=fr"

    val gson = Gson()
    val client = OkHttpClient()

    fun loadWeather(city: String): WeatherBean {
        //Eventuel contrôle
        //Réaliser la requête
        val json: String = sendGet(API_URL + city + API_PARAMS)

        //Parser le JSON avec le bon bean et GSON
        val data : WeatherBean = gson.fromJson(json, WeatherBean::class.java)

        //Eventuel contrôle ou extraction de données

        //Retourner la donnée
        return data
    }

    //Méthode qui prend en entrée une url, execute la requête
    //Retourne le code HTML/JSON reçu
    fun sendGet(url: String): String {
        println("url : $url")
        val request = Request.Builder().url(url).build()
        return client.newCall(request).execute().use { //it:Response
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect :${it.code}")
            }
            it.body.string()
        }
    }
}